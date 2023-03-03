package org.nwpu.match.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

/**
 * 招生计划类
 * @author lzy
 */
@Data
public class Plan {

    /**
     * 学硕招生类型代码
     */
    public static final int A = 0;

    /**
     * 专硕招生类型代码
     */
    public static final int B = 1;

    /**
     * 直博招生类型代码
     */
    public static final int C = 2;

    /**
     * 招生计划id
     */
    private Integer id;

    /**
     * 计划中对应专业的id
     */
    private Integer mid;

    /**
     * 学硕招生人数
     */
    private Integer numberA;

    /**
     * 专硕招生人数
     */
    private Integer numberB;

    /**
     * 直博招生人数
     */
    private Integer numberC;

    /**
     * 总招生人数
     */
    private Integer number;

    /**
     * 将plan实例转化为json字符串
     * @return 转化结果
     */
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * 将JSON字符串解析为Plan对象实例
     * @param json 待解析字符串
     * @return 解析结果
     */
    public static Plan toObject(String json){
        return JSON.parseObject(json,Plan.class);
    }
}
