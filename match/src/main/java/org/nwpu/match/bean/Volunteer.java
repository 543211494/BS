package org.nwpu.match.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

/**
 * 志愿类
 * @author lzy
 */
@Data
public class Volunteer {

    /**
     * 志愿id
     */
    private Integer id;

    /**
     * 专业id
     */
    private Integer mid;

    /**
     * 总成绩
     */
    private Double finalGrade;

    /**
     * 报名类型
     */
    private Integer type;

    /**
     * 将volunteer实例转化为json字符串
     * @return 转化结果
     */
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * 将JSON字符串解析为Volunteer对象实例
     * @param json 待解析字符串
     * @return 解析结果
     */
    public static Volunteer toObject(String json){
        return JSON.parseObject(json,Volunteer.class);
    }
}
