package org.nwpu.user.controller;

import org.nwpu.user.bean.User;
import org.nwpu.user.bean.Response;
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
 * 用户操作控制类
 * @author lzy
 */
@Controller
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    /**
     * 退出登录
     * @param token 登录令牌
     * @return 执行结果
     */
    @RequestMapping(value = "/api/user/user-service/logout",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String logout(@RequestParam("token")String token){
        /* 删除token以退出登录 */
        redisTemplate.delete(token);
        Response response = new Response<Object>();
        response.setData(null);
        return response.toString();
    }
}
