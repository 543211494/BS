package org.nwpu.user.controller;

import org.nwpu.user.bean.Response;
import org.nwpu.user.util.QiniuOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 报名控制类
 * @author lzy
 */
@Controller
@ResponseBody
public class SignupController {

    @Autowired
    private QiniuOperator qiniuOperator;

    @RequestMapping(value = "/api/user-service/test",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String test(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        String url = qiniuOperator.uploadImage(file, file.getOriginalFilename());
        Response response = new Response<Object>();
        response.setData(url);
        return response.toString();
    }
}
