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
        MESSAGE.put(204,"参数错误！");
        MESSAGE.put(205,"参数格式错误！");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String processError(Exception e){
        int code = 400;
        /* 其他错误 */
        try{
            code = Integer.valueOf(e.getMessage());
        }catch (Exception exception){
            exception.printStackTrace();
        }
        String message = MESSAGE.get(code);
        if (message==null){
            message = e.getMessage();
        }
        Response response = new Response<Object>();
        response.setCode(code);
        response.setMessage(message);
        return response.toString();
    }
}
