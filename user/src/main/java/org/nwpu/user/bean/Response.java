package org.nwpu.user.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

/**
 * 消息回复实体类
 * @author lzy
 * @param <T> 返回的数据对象
 */
@Data
public class Response<T> {

    /**
     * 错误码：登录错误
     */
    public static final String LOGIN_ERROR = "201";

    /**
     * 错误码：无效token
     */
    public static final String TOKEN_ERROR = "202";

    /**
     * 错误码：权限错误
     */
    public static final String NO_POWER_ERROR = "203";

    /**
     * 消息码
     */
    private Integer code;

    /**
     * 消息信息
     */
    private String message;

    /**
     * 消息数据体
     */
    private T data;

    public Response() {
        this.code = 200;
        this.message = "OK";
    }

    /**
     * 将response实例转化为json字符串
     * @return 转化结果
     */
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }
}

