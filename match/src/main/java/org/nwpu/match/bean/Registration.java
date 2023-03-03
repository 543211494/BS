package org.nwpu.match.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

/**
 * 报名表类
 * @author lzy
 */
@Data
public class Registration {

    /**
     * 待录取
     */
    public static final Integer UNACCEPTED = 0;

    /**
     * 已录取
     */
    public static final Integer ACCEPTED = 1;

    /**
     * 所有志愿已处理完但未录取
     */
    public static final Integer PROCESSED = 2;

    /**
     * 报名表id
     */
    private Integer id;

    /**
     * 报名表所属用户id
     */
    private Integer uid;

    /**
     * 报名表状态
     */
    private Integer status;

    /**
     * 报名表当前处理到的志愿
     */
    private Integer current;

    /**
     * 录取类型,0学，1专，2直
     */
    private Integer admissionType;

    /**
     * 报名表当前志愿
     */
    private Volunteer currentVolunteer;

    /**
     * 包含的志愿个数
     */
    private Integer volunteerNumber;

    /**
     * 将registration实例转化为json字符串
     * @return 转化结果
     */
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * 将JSON字符串解析为Registration对象实例
     * @param json 待解析字符串
     * @return 解析结果
     */
    public static Registration toObject(String json){
        return JSON.parseObject(json,Registration.class);
    }
}
