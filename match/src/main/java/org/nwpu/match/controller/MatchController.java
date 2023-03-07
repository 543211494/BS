package org.nwpu.match.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.nwpu.match.bean.Plan;
import org.nwpu.match.bean.Registration;
import org.nwpu.match.bean.Response;
import org.nwpu.match.config.RabbitMQConfig;
import org.nwpu.match.service.MatchService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 匹配控制类
 * @author lzy
 */
@Controller
@ResponseBody
public class MatchController {

    /**
     * 匹配锁的key值
     */
    public static final String MATCH_KEY = "match-lock";

    /**
     * 匹配年份的key值
     */
    public static final String MATCH_YEAR = "match-year";

    /**
     * 存储发生匹配消息次数的key值
     */
    public static final String MATCH_NUMBER = "match-number";

    /**
     * 修改发生匹配消息次数的key值时用的分布式锁
     */
    public static final String MATCH_NUMBER_LOCK = "match-number-lock";

    /**
     * 每轮发送消息的个数
     */
    public static final int NUMBER = 4;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private MatchService matchService;

    @RequestMapping(value = "/api/admin/match-service/startMatch",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String start(){
        if(!redisTemplate.opsForValue().setIfAbsent(MATCH_KEY,"lock")){
            throw new RuntimeException(Response.REPEAT_MATCH_ERROR);
        }
        /* 招生年份 */
        Integer year = Integer.valueOf((String)redisTemplate.opsForValue().get("year"));
        /* 报名未开始 */
        if (year==null||year<0){
            redisTemplate.delete(MATCH_KEY);
            throw new RuntimeException(Response.TIME_ERROR);
        }
        /* 关闭报名通道 */
        redisTemplate.opsForValue().set("year","-1");
        /* 防止重复开始匹配 */
        redisTemplate.opsForValue().set(MATCH_YEAR,String.valueOf(year));
        this.sendMessages(year);
        Response response = new Response();
        return response.toString();
    }

    /**
     * 用于测试匹配算法性能的接口,正式版本需删除
     * @return
     */
    @RequestMapping(value = "/api/match-service/test",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String test(@RequestParam("type")Integer type){
        redisTemplate.opsForValue().set("year","2022");
        redisTemplate.delete(MATCH_KEY);
        redisTemplate.delete(MATCH_YEAR);
        redisTemplate.delete(MATCH_NUMBER);
        if(type.intValue()==1){
            redisTemplate.opsForValue().set("start",String.valueOf(new Date().getTime()));
            return this.start();
        }else {
            redisTemplate.opsForValue().set("start",String.valueOf(new Date().getTime()));
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.TEST_ROUTING_KEY,"test");
            return "test";
        }
    }

    /**
     * 用于测试算法的消息处理函数，正式版本需删除
     */
    @RabbitListener(queues = RabbitMQConfig.TEST_QUEUE_NAME)
    public void testMatch(Message message){
        if(!redisTemplate.opsForValue().setIfAbsent(MATCH_KEY,"lock")){
            throw new RuntimeException(Response.REPEAT_MATCH_ERROR);
        }
        /* 招生年份 */
        Integer year = Integer.valueOf((String)redisTemplate.opsForValue().get("year"));
        /* 报名未开始 */
        if (year==null||year<0){
            redisTemplate.delete(MATCH_KEY);
            throw new RuntimeException(Response.TIME_ERROR);
        }
        /* 关闭报名通道 */
        redisTemplate.opsForValue().set("year","-1");
        /* 防止重复开始匹配 */
        redisTemplate.opsForValue().set(MATCH_YEAR,String.valueOf(year));
        List<Plan> plans = matchService.searchPlans(year);
        List<Registration> registrations;
        Registration registration;
        Plan plan;
        while (matchService.hasUnprocessedRegistration(year)){
            for(int i = 0;i<plans.size();i++){
                plan = plans.get(i);
                registrations = matchService.searchRegistrationsByMajor(year,plans.get(i).getMid());
                for(int j = 0;j<registrations.size();j++){
                    registration = registrations.get(j);
                    /* 更新报名表状态 */
                    if(0<=j&&j<plan.getNumberA()){
                        registration.setStatus(Registration.ACCEPTED);
                        registration.setAdmissionType(Plan.A);
                    }else if(j<(plan.getNumberA()+plan.getNumberB())){
                        registration.setStatus(Registration.ACCEPTED);
                        registration.setAdmissionType(Plan.B);
                    }else if(j<plan.getNumber()){
                        registration.setStatus(Registration.ACCEPTED);
                        registration.setAdmissionType(Plan.C);
                    } else {
                        registration.setCurrent(registration.getCurrent()+1);
                        if(registration.getCurrent()<=registration.getVolunteerNumber()){
                            registration.setStatus(Registration.UNACCEPTED);
                        }else {
                            registration.setStatus(Registration.PROCESSED);
                        }
                    }
                }
                /*  更新数据库 */
                matchService.updateRegistration(registrations);
            }
        }
        /* 所有志愿均已处理完 */
        matchService.insertAdmission(year);
//        long start = Long.valueOf((String)redisTemplate.opsForValue().get("start")).longValue();
//        long end = new Date().getTime();
//        redisTemplate.opsForValue().set("time",String.valueOf(end-start));
    }

    /**
     * 处理消息
     * @param message 消息
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processMessage(Message message){
        //System.out.println(new String(message.getBody()));
        List<Plan> plans = JSON.parseArray(new String(message.getBody()),Plan.class);
        Integer year = Integer.valueOf((String) redisTemplate.opsForValue().get(MATCH_YEAR));
        List<Registration> registrations;
        Registration registration;
        Plan plan;
        for(int i = 0;i<plans.size();i++){
            plan = plans.get(i);
            registrations = matchService.searchRegistrationsByMajor(year,plans.get(i).getMid());
            for(int j = 0;j<registrations.size();j++){
                registration = registrations.get(j);
                /* 更新报名表状态 */
                if(0<=j&&j<plan.getNumberA()){
                    registration.setStatus(Registration.ACCEPTED);
                    registration.setAdmissionType(Plan.A);
                }else if(j<(plan.getNumberA()+plan.getNumberB())){
                    registration.setStatus(Registration.ACCEPTED);
                    registration.setAdmissionType(Plan.B);
                }else if(j<plan.getNumber()){
                    registration.setStatus(Registration.ACCEPTED);
                    registration.setAdmissionType(Plan.C);
                } else {
                    registration.setCurrent(registration.getCurrent()+1);
                    if(registration.getCurrent()<=registration.getVolunteerNumber()){
                        registration.setStatus(Registration.UNACCEPTED);
                    }else {
                        registration.setStatus(Registration.PROCESSED);
                    }
                }
            }
            /*  更新数据库 */
            matchService.updateRegistration(registrations);
        }
        this.lock();
        Integer matchNumber = Integer.valueOf((String) redisTemplate.opsForValue().get(MATCH_NUMBER));
        matchNumber--;
        redisTemplate.opsForValue().set(MATCH_NUMBER,String.valueOf(matchNumber));
        this.unlock();
        /* 如果是该轮匹配最后一条处理完的消息 */
        if(matchNumber.intValue()==0){
            if(matchService.hasUnprocessedRegistration(year)){
                /* 还有志愿待处理，开始下一轮匹配 */
                this.sendMessages(year);
            }else{
                /* 所有志愿均已处理完 */
                matchService.insertAdmission(year);
                redisTemplate.delete(MATCH_KEY);
//                long start = Long.valueOf((String)redisTemplate.opsForValue().get("start")).longValue();
//                long end = new Date().getTime();
//                redisTemplate.opsForValue().set("time",String.valueOf(end-start));
            }
        }
    }

    /**
     * 发送消息开始一轮匹配
     * @param year
     */
    public void sendMessages(Integer year){
        //System.out.println("=========================================");
        List<Plan> plans = matchService.searchPlans(year);
        List<Plan> message = new ArrayList<Plan>();
        /* number每条消息包含的最多plan数目 */
        int number = plans.size()/NUMBER;
        if(number%NUMBER!=0){
            number++;
        }
        redisTemplate.opsForValue().set(MATCH_NUMBER,String.valueOf(number));
        for(int i = 0;i<plans.size();i++){
            if (message.size()==number){
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.ROUTING_KEY,JSON.toJSONString(message, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty));
                message = new ArrayList<Plan>();
            }
            message.add(plans.get(i));
        }
        if(!message.isEmpty()){
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.ROUTING_KEY,JSON.toJSONString(message, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty));
        }
    }

    /**
     * 修改一轮匹配中已处理消息的数目时加锁
     */
    public void lock() {
        boolean result = false;
        while (!result){
            result = redisTemplate.opsForValue().setIfAbsent(MATCH_NUMBER_LOCK,"lock");
            try{
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改一轮匹配中已处理消息的数目时解锁
     */
    public void unlock(){
        redisTemplate.delete(MATCH_NUMBER_LOCK);
    }
}
