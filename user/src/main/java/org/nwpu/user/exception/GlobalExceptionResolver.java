package org.nwpu.user.exception;

import org.nwpu.user.bean.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 * @author lzy
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    public static final Map<Integer,String> MESSAGE = new HashMap<>();
    static {
        MESSAGE.put(201,"登录信息有误！");
        MESSAGE.put(202,"无效token或登录已过期！");
        MESSAGE.put(203,"权限不足！");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String processError(Exception e){
        int code = Integer.valueOf(e.getMessage());
        Response response = new Response<Object>();
        response.setCode(code);
        response.setMessage(MESSAGE.get(code));
        response.setData(null);
        return response.toString();
    }
}
