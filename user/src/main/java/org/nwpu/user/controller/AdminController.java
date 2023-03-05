package org.nwpu.user.controller;

import org.nwpu.user.bean.Response;
import org.nwpu.user.bean.User;
import org.nwpu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员控制类
 * @author lzy
 */
@Controller
@ResponseBody
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * 重置用户密码
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/api/admin/user-service/resetPassword",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String resetPassword(@RequestParam("userId")Integer userId){
        userService.resetPassword(userId);
        Response response = new Response<Object>();
        return response.toString();
    }

    /**
     * 删除用户
     * @param token 用户登录令牌
     * @param userId 要删除用户的用户id
     * @return
     */
    @RequestMapping(value = "/api/super/user-service/deleteUser",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String deleteUser(@RequestParam("token")String token,@RequestParam("userId")Integer userId){
        User user = userService.searchUserById(userId);
        if(user==null){
            throw new RuntimeException(Response.PARAMETER_ERROR);
        }
        if(user.getPower().equals(User.SUPER)){
            throw new RuntimeException(Response.DELETE_USER_ERROR);
        }
        userService.deleteUserById(userId);
        Response response = new Response<Object>();
        return response.toString();
    }

    /**
     * 新增用户
     * @param identity 用户身份证号
     * @param power 用户权限
     * @param fullName 用户姓名
     * @param sex 用户性别
     * @param mid 用户专业id
     * @param studentId 用户学号
     * @param qualification 用户保研资格
     * @param year 用户保研年份
     * @return
     */
    @RequestMapping(value = "/api/super/user-service/addUser",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String addUser(@RequestParam("identity")String identity,@RequestParam("power")String power,
                          @RequestParam("fullName")String fullName,@RequestParam("sex")String sex,
                          @RequestParam("mid")Integer mid,
                          @RequestParam(value = "studentId",required = false)String studentId,
                          @RequestParam(value = "qualification",required = false)Integer qualification,
                          @RequestParam(value = "year",required = false)Integer year){
        if(identity==null||power==null||fullName==null||sex==null||mid==null
                ||(!power.equals(User.ADMIN)&&!power.equals(User.TEACHER)&&!power.equals(User.USER))){
            throw new RuntimeException(Response.PARAMETER_ERROR);
        }
        User user = new User(identity,power,fullName,studentId,sex,qualification,year);
        try{
            userService.insertUser(user,mid);
        }catch (Exception e){
            //e.printStackTrace();
            throw new RuntimeException(Response.REPEAT_USER_ERROR);
        }
        Response response = new Response<Object>();
        response.setData(user.getId());
        return response.toString();
    }
}
