package org.nwpu.user.bean;

import java.util.ArrayList;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.List;

/**
 * 报名表类
 * @author lzy
 */
@Data
public class Registration {

    /**
     * 报名表id
     */
    private Integer id;

    /**
     * 报名者照片url
     */
    private String photo;

    /**
     * 报名者联系地址
     */
    private String address;

    /**
     * 报名者联系电话
     */
    private String phone;

    /**
     * 当前处理到的志愿
     */
    private Integer current;

    /**
     * 填报年份
     */
    private Integer year;

    /**
     * 报考类型,1是保研，0是考研
     */
    private Integer type;

    /**
     * 志愿
     */
    private List<Volunteer> volunteers;

    /**
     * 报名已经进行到第几步
     */
    private Integer step;

    /**
     * 包含的志愿个数
     */
    private Integer volunteerNumber;

    /**
     * 报名是否已经最终确定
     */
    private boolean isFinished;

    public Registration() {
        this.step = 1;
        this.current = 1;
        this.type = 1;
        this.isFinished = false;
        this.volunteerNumber = 0;
    }

    /**
     * 将registration实例转化为json字符串
     * @return 转化结果
     */
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * 将JSON字符串解析为registration对象实例
     * @param json 待解析字符串
     * @return 解析结果
     */
    public static Registration toObject(String json){
        return JSON.parseObject(json,Registration.class);
    }
}
