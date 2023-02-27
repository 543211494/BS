package org.nwpu.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import org.nwpu.user.bean.Major;
import org.nwpu.user.bean.Registration;
import org.nwpu.user.bean.Response;
import org.nwpu.user.bean.Volunteer;
import org.nwpu.user.service.SignupService;
import org.nwpu.user.util.QiniuOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * 报名控制类
 * @author lzy
 */
@Controller
@ResponseBody
public class SignupController {

    @Autowired
    private QiniuOperator qiniuOperator;

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private SignupService signupService;

//    @RequestMapping(value = "/api/user-service/test",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
//    public String test(@RequestParam("test")Integer[] mids) {
//        for (int i=0;i<mids.length;i++){
//            System.out.println(mids[i]);
//        }
//        HashMap<String ,Object > map = new HashMap<>();
//        Response response = new Response<Object>();
//        response.setData(map);
//        return response.toString();
//    }

    /**
     * 保存报名照片
     * @param photo 图片文件
     * @param token 登录令牌
     * @param step 报名执行到的步骤
     * @param type 报名类型
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/api/user/user-service/saveImage",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String saveImage(@RequestParam("photo")MultipartFile photo,@RequestParam("token")String token,
                            @RequestParam("step")Integer step,@RequestParam("type")Integer type) throws IOException {
        /* 校验数据 */
        if(photo==null||step==null||type==null){
            throw new RuntimeException(Response.PARAMETER_ERROR);
        }
        String[] fileName = photo.getOriginalFilename().split("\\.");
        String extension = fileName[fileName.length-1];
        if(!extension.equals("jpg")&&!extension.equals("png")){
            throw new RuntimeException(Response.FORMAT_ERROR);
        }
        /* 招生年份 */
        Integer year = Integer.valueOf((String)redisTemplate.opsForValue().get("year"));
        Integer id = Integer.valueOf(token.split("-")[1]);
        /* redis中的key */
        String key = "registration-"+year+"-"+id;
        Registration registration = this.createRegistration(key,year,step,type);
        /* 图床中的文件名 */
        String photoName = UUID.randomUUID()+"."+extension;
        /* 执行操作 */
        if(registration.getPhoto()!=null){
            /* 覆盖提交则删除旧文件 */
            qiniuOperator.delete(registration.getPhoto());
        }
        String url = qiniuOperator.upload(photo, photoName);
        registration.setPhoto(url);
        redisTemplate.opsForValue().set(key,registration.toString());
        Response response = new Response<Object>();
        response.setData(registration);
        return response.toString();
    }

    /**
     * 保存联系方式
     * @param token 登录令牌
     * @param address 联系地址
     * @param phone 联系电话
     * @param step 报名执行到的步骤
     * @param type 报名类型
     * @return
     */
    @RequestMapping(value = "/api/user/user-service/saveInfo",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String saveInfo(@RequestParam("token")String token,@RequestParam("address")String address,
                           @RequestParam("phone")String phone,@RequestParam("step")Integer step,
                           @RequestParam("type")Integer type){
        /* 校验数据 */
        if(address==null||phone==null||step==null||type==null){
            throw new RuntimeException(Response.PARAMETER_ERROR);
        }
        /* 招生年份 */
        Integer year = Integer.valueOf((String)redisTemplate.opsForValue().get("year"));
        Integer id = Integer.valueOf(token.split("-")[1]);
        /* redis中的key */
        String key = "registration-"+year+"-"+id;
        Registration registration = this.createRegistration(key,year,step,type);
        /* 执行操作 */
        registration.setAddress(address);
        registration.setPhone(phone);
        redisTemplate.opsForValue().set(key,registration.toString());
        Response response = new Response<Object>();
        response.setData(registration);
        return response.toString();
    }

    /**
     * 保存志愿
     * @param token 用户登录令牌
     * @param majors 专业id数组
     * @param types 志愿类型数组
     * @param step 报名进行到的步骤数
     * @param type 报名类型
     * @return
     */
    @RequestMapping(value = "/api/user/user-service/saveVolunteers",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String saveVolunteers(@RequestParam("token")String token,@RequestParam("majors")Integer[] majors,
                                 @RequestParam("types")Integer[] types,@RequestParam("step")Integer step,
                                 @RequestParam("type")Integer type){
        /* 校验数据 */
        if(majors==null||types==null||majors.length!=types.length||step==null||type==null){
            throw new RuntimeException(Response.PARAMETER_ERROR);
        }
        /* 招生年份 */
        Integer year = Integer.valueOf((String)redisTemplate.opsForValue().get("year"));
        Integer id = Integer.valueOf(token.split("-")[1]);
        /* redis中的key */
        String key = "registration-"+year+"-"+id;
        Registration registration = this.createRegistration(key,year,step,type);
        /* 执行操作 */
        List<Major> majorList = signupService.searchMajorsById(majors);
        List<Volunteer> volunteerList = new ArrayList<Volunteer>();
        Volunteer volunteer;
        for(int i=0;i<majors.length;i++){
            volunteer = new Volunteer();
            volunteer.setRank(i+1);
            volunteer.setType(types[i]);
            for(int j = 0;j<majorList.size();j++){
                if(majorList.get(j).getId().intValue()==majors[i]){
                    volunteer.setMajor(majorList.get(j));
                    break;
                }
            }
            volunteerList.add(volunteer);
        }
        registration.setVolunteers(volunteerList);
        redisTemplate.opsForValue().set(key,registration.toString());
        Response response = new Response<Object>();
        response.setData(registration);
        return response.toString();
    }

    /**
     * 最终提交报名表
     * @param token 用户登录令牌
     * @param step 报名进行到的步骤数
     * @param type 报名类型
     * @return
     */
    @RequestMapping(value = "/api/user/user-service/submitRegistration",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String submitRegistration(@RequestParam("token")String token,@RequestParam("step")Integer step,
                                     @RequestParam("type")Integer type){
        /* 招生年份 */
        Integer year = Integer.valueOf((String)redisTemplate.opsForValue().get("year"));
        Integer id = Integer.valueOf(token.split("-")[1]);
        /* redis中的key */
        String key = "registration-"+year+"-"+id;
        Registration registration = this.createRegistration(key,year,step,type);
        if(registration.isFinished()){
            throw new RuntimeException(Response.REPEAT_REGISTRATION_ERROR);
        }else if (registration.getPhoto()==null||registration.getAddress()==null||registration.getVolunteers()==null||registration.getVolunteers().isEmpty()){
            throw new RuntimeException(Response.REGISTRATION_INFO_ERROR);
        }
        signupService.insertRegistration(registration,id);
        registration.setFinished(true);
        redisTemplate.opsForValue().set(key,registration.toString());
        Response response = new Response<Object>();
        response.setData(registration);
        return response.toString();
    }

    /**
     * 查看用户当年的报名表
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/api/user/user-service/getRegistration",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getRegistration(@RequestParam("token")String token){
        /* 招生年份 */
        Integer year = Integer.valueOf((String)redisTemplate.opsForValue().get("year"));
        /* 报名未开始 */
        if (year==null||year<0){
            throw new RuntimeException(Response.TIME_ERROR);
        }
        Integer id = Integer.valueOf(token.split("-")[1]);
        /* redis中的key */
        String key = "registration-"+year+"-"+id;
        Registration registration = Registration.toObject((String) redisTemplate.opsForValue().get(key));
        if(registration==null){
            registration = signupService.searchRegistrationByUserId(id,year);
            if (registration!=null){
                registration.setStep(5);
                registration.setFinished(true);
            }else {
                registration = new Registration();
                registration.setStep(1);
                registration.setFinished(false);
            }
            redisTemplate.opsForValue().set(key,registration.toString());
        }
        Response response = new Response<Object>();
        response.setData(registration);
        return response.toString();
    }

    /**
     * 查询全部专业
     * @return
     */
    @RequestMapping(value = "/api/user-service/getAllMajor",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getAllMajor(){
        String key = "major";
        List<Major> majors = JSON.parseArray((String) redisTemplate.opsForValue().get(key),Major.class);
        if(majors==null){
            majors = signupService.searchAllMajors();
            redisTemplate.opsForValue().set(key,JSON.toJSONString(majors, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty));
        }
        Response response = new Response<Object>();
        response.setData(majors);
        return response.toString();
    }

    /**
     * 暂存报名表的公共操作，从redis中获取registration对象并进行校验
     *
     * @param key key值
     * @param year 招生报名年份
     * @param step 报名执行到的步骤
     * @param type 报名类型
     * @return 报名表对象
     */
    public Registration createRegistration(String key,Integer year,Integer step,Integer type){
        /* 报名未开始 */
        if (year==null||year<0){
            throw new RuntimeException(Response.TIME_ERROR);
        }
        Registration registration = Registration.toObject((String) redisTemplate.opsForValue().get(key));
        if (registration==null){
            registration = new Registration();
            registration.setYear(year);
            registration.setType(type);
        }else if(registration.getType().intValue()!=type.intValue()){
            throw new RuntimeException(Response.PARAMETER_ERROR);
        }
        if(registration.getStep()<step){
            registration.setStep(step);
        }
        return registration;
    }
}
