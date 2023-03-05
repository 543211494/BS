package org.nwpu.user.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

/**
 * 录取类
 * @author lzy
 */
@Data
public class Admission {

    /**
     * 录取id
     */
    private Integer id;

    /**
     * 录取专业id
     */
    private Integer mid;

    /**
     * 录取专业招生代码
     */
    private String code;

    /**
     * 录取专业所属大学
     */
    private String university;

    /**
     * 录取专业所属学院
     */
    private String college;

    /**
     * 录取专业专业名称
     */
    private String majorName;

    /**
     * 录取年份
     */
    private Integer year;

    /**
     * 录取类型
     */
    private Integer type;

    /**
     * 将admission实例转化为json字符串
     * @return 转化结果
     */
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * 将JSON字符串解析为admission对象实例
     * @param json 待解析字符串
     * @return 解析结果
     */
    public static Admission toObject(String json){
        return JSON.parseObject(json,Admission.class);
    }
}
