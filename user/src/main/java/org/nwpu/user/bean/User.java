package org.nwpu.user.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.UUID;

/**
 * 用户实体类
 * @author lzy
 */
@Data
public class User {

    /**
     * 用户权限标识，管理管理员的管理员
     */
    public static final String SUPER = "super";

    /**
     * 用户权限标识，管理员
     */
    public static final String ADMIN = "admin";

    /**
     * 用户权限标识，招生老师
     */
    public static final String TEACHER = "teacher";

    /**
     * 用户权限标识，考生
     */
    public static final String USER = "user";

    /**
     * 用户id
     */
    private int id;

    /**
     * 用户身份证号
     */
    private String identity;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户权限
     */
    private String power;

    /**
     * 用户所属大学
     */
    private String university;

    /**
     * 用户所属学院
     */
    private String college;

    /**
     * 用户专业名称
     */
    private String majorName;

    /**
     * 用户姓名
     */
    private String fullName;

    /**
     * 用户学号
     */
    private String studentId;

    /**
     * 用户年龄
     */
    private int age;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 是否有保研资格
     */
    private int qualification;

    /**
     * 资格对应年份
     */
    private int year;

    /**
     * 将user实例转化为json字符串
     * @return 转化结果
     */
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * 生成登陆令牌,使用uuid作为token,保障token的唯一性
     * @return token
     */
    public String token(){
        return "token-"+this.id+"-"+UUID.randomUUID().toString();
    }

    /**
     * 将JSON字符串解析为User对象实例
     * @param json 待解析字符串
     * @return 解析结果
     */
    public static User toObject(String json){
        return JSON.parseObject(json,User.class);
    }

    /**
     * 构造函数
     */
    public User() {
    }

    /**
     * 构造函数
     */
    public User(int id, String identity, String password, String email, String power, String university, String college, String majorName, String fullName, String studentId, int age, String sex, int qualification, int year) {
        this.id = id;
        this.identity = identity;
        this.password = password;
        this.email = email;
        this.power = power;
        this.university = university;
        this.college = college;
        this.majorName = majorName;
        this.fullName = fullName;
        this.studentId = studentId;
        this.age = age;
        this.sex = sex;
        this.qualification = qualification;
        this.year = year;
    }
}
