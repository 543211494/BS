package org.nwpu.user.bean;

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
     * 第几志愿
     */
    private Integer rank;

    /**
     * 专业
     */
    private Major major;

    /**
     * 初试得分
     */
    private Double firstGrade;

    /**
     * 复试得分
     */
    private Double secondGrade;

    /**
     * 最终得分
     */
    private Double finalGrade;

    /**
     * 招生类型,0学，1专，2直
     */
    private Integer type;

    public Volunteer() {
        this.firstGrade = 0.0;
        this.secondGrade = 0.0;
        this.finalGrade = 0.0;
    }

    /**
     * 将volunteer实例转化为json字符串
     * @return 转化结果
     */
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * 将JSON字符串解析为volunteer对象实例
     * @param json 待解析字符串
     * @return 解析结果
     */
    public static Volunteer toObject(String json){
        return JSON.parseObject(json,Volunteer.class);
    }
}
