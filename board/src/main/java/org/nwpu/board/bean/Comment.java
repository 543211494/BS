package org.nwpu.board.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 留言实体类
 * @author lzy
 */
@Data
public class Comment {

    /**
     * 留言id
     */
    private Integer id;

    /**
     * 留言作者的用户id
     */
    private Integer uid;

    /**
     * 留在作者姓名
     */
    private String userName;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
      子留言列表
     */
    private List<Comment> comments;

    public Comment() {
        this.publishTime = new Date();
    }

    public Comment(Integer uid, String userName, String content) {
        this.publishTime = new Date();
        this.uid = uid;
        this.userName = userName;
        this.content = content;
    }

    /**
     * 将comment实例转化为json字符串
     * @return 转化结果
     */
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * 将JSON字符串解析为Comment对象实例
     * @param json 待解析字符串
     * @return 解析结果
     */
    public static Comment toObject(String json){
        return JSON.parseObject(json,Comment.class);
    }
}
