package org.nwpu.user.controller;

import org.nwpu.user.bean.Response;
import org.nwpu.user.bean.User;
import org.nwpu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 公共操作控制类,无须登录即可执行的操作
 * @author lzy
 */
@Controller
@ResponseBody
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

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
        String token = user.token();
        redisTemplate.opsForValue().set(token,user.toString(),1, TimeUnit.HOURS);
        user.setPassword(null);
        Response response = new Response<Object>();
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("token",token);
        data.put("user",user);
        response.setData(data);
        return response.toString();
    }
}
