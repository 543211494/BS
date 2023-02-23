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
        return response.toString();
    }

    /**
     * 修改密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param token 登录令牌
     * @return 执行结果
     */
    @RequestMapping(value = "/api/user/user-service/updatePassword",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String updatePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,@RequestParam("token")String token){
        User user = User.toObject((String) redisTemplate.opsForValue().get(token));
        if (user==null){
            throw new RuntimeException(Response.TOKEN_ERROR);
        }
        if (user.getPassword().equals(oldPassword)){
            userService.updatePassword(user.getId(),newPassword);
        }else {
            throw new RuntimeException(Response.PARAMETER_ERROR);
        }
        Response response = new Response<Object>();
        return response.toString();
    }

    /**
     * 修改邮箱信息
     * @param email 新邮箱
     * @param token 登录令牌
     * @return 执行结果
     */
    @RequestMapping(value = "/api/user/user-service/updateEmail",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String updateEmail(@RequestParam("email") String email,@RequestParam("token")String token){
        int id = Integer.valueOf(token.split("-")[1]);
        userService.updateEmail(id,email);
        Response response = new Response<Object>();
        return response.toString();
    }
}
