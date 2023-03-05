package org.nwpu.match.bean;

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
     * 错误码：参数错误
     */
    public static final String PARAMETER_ERROR = "204";

    /**
     * 错误码：格式错误
     */
    public static final String FORMAT_ERROR = "205";

    /**
     * 错误码：时间错误
     */
    public static final String TIME_ERROR = "206";

    /**
     * 错误码：提交报名表错误
     */
    public static final String REGISTRATION_INFO_ERROR = "207";

    /**
     * 错误码：重复报名错误
     */
    public static final String REPEAT_REGISTRATION_ERROR = "208";

    /**
     * 错误码：无报名资格错误
     */
    public static final String NO_QUALIFICATION_ERROR = "209";

    /**
     * 错误码：重复匹配错误
     */
    public static final String REPEAT_MATCH_ERROR = "210";

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
        this.data=null;
    }

    /**
     * 将response实例转化为json字符串
     * @return 转化结果
     */
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }
}

