package org.nwpu.user.bean;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

/**
 * 专业类
 * @author lzy
 */
@Data
public class Major {

    /**
     * 专业id
     */
    private Integer id;

    /**
     * 专业招生代码
     */
    private String code;

    /**
     * 志愿专业所属大学
     */
    private String university;

    /**
     * 志愿专业所属学院
     */
    private String college;

    /**
     * 志愿专业专业名称
     */
    private String majorName;

    /**
     * 专业评级
     */
    private String level;

    /**
     * 将major实例转化为json字符串
     * @return 转化结果
     */
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * 将JSON字符串解析为major对象实例
     * @param json 待解析字符串
     * @return 解析结果
     */
    public static Major toObject(String json){
        return JSON.parseObject(json,Major.class);
    }
}
