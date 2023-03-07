package org.nwpu.user.controller;

import org.nwpu.user.bean.Response;
import org.nwpu.user.bean.User;
import org.nwpu.user.service.UserService;
import org.nwpu.user.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 公共操作控制类,无须登录即可执行的操作
 * @author lzy
 */
@Controller
@ResponseBody
public class HomeController {

    /**
     * 验证码长度
     */
    private int length = 6;

    /**
     * 用于随机数
     */
    private Random random = new Random();

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private EmailSender emailSender;

    /**
     * 登录操作
     * @param userName 用户名
     * @param password 密码
     * @return 执行结果
     */
    @RequestMapping(value = "/api/user-service/login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String login(@RequestParam("userName")String userName, @RequestParam("password")String password){
        User user = userService.searchUserByUserName(userName);
        if(user==null||password==null||password.isEmpty()||!password.equals(user.getPassword())){
            throw new RuntimeException(Response.LOGIN_ERROR);
        }
        /* 生成token并存储于redis中 */
        String token;
        Set<String> tokens=redisTemplate.keys("token-"+user.getId()+"*");
        /* 最多三点登录 */
        if(tokens.size()>=3){
            token = tokens.iterator().next();
        }else {
            token = user.token();
        }
        redisTemplate.opsForValue().set(token,user.toString(),1, TimeUnit.HOURS);
        user.setPassword(null);
        Response response = new Response<Object>();
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("token",token);
        data.put("user",user);
        response.setData(data);
        return response.toString();
    }

    /**
     * 获取修改密码的验证码
     * @param identity
     * @return
     */
    @RequestMapping(value = "/api/user-service/getCode",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String getCode(@RequestParam("identity")String identity){
        User user = userService.searchUserByUserName(identity);
        if(user==null){
            throw new RuntimeException(Response.PARAMETER_ERROR);
        }
        Response response = new Response<Object>();
        Map<String,Object> data = new HashMap<String,Object>();
        if(user.getEmail()==null||!EmailSender.isEmail(user.getEmail())){
            throw new RuntimeException(Response.EMAIL_ERROR);
        }else{
            StringBuffer buffer = new StringBuffer();
            for(int i = 0;i<this.length;i++){
                buffer.append(random.nextInt(10));
            }
            String code = buffer.toString();
            emailSender.send(user.getEmail(),code,"修改密码");
            redisTemplate.opsForValue().set("code-"+user.getIdentity(),code,2, TimeUnit.MINUTES);
            data.put("email",user.getEmail());
        }
        response.setData(data);
        return response.toString();
    }

    /**
     * 通过验证码修改密码
     * @return
     */
    @RequestMapping(value = "/api/user-service/resetPassword",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String resetPassword(@RequestParam("identity")String identity,@RequestParam("password")String password,
                                @RequestParam("code")String code){
        if(identity==null||code==null||password==null){
            throw new RuntimeException(Response.PARAMETER_ERROR);
        }
        User user = userService.searchUserByUserName(identity);
        if(user==null){
            throw new RuntimeException(Response.PARAMETER_ERROR);
        }
        String str = (String)redisTemplate.opsForValue().get("code-"+user.getIdentity());
        if(str==null||!str.equals(code)){
            throw new RuntimeException(Response.CODE_ERROR);
        }else{
            userService.updatePassword(user.getId(), password);
            redisTemplate.delete("code-"+user.getIdentity());
        }
        Response response = new Response<Object>();
        return response.toString();
    }
}
