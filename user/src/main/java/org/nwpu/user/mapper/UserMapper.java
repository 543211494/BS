package org.nwpu.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.user.bean.User;

/**
 * 用户操作mapper
 * @author lzy
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 查询结果
     */
    public User searchUserByUserName(@Param("userName") String userName);

    /**
     * 修改密码
     * @param id 待修改用户的id
     * @param password 新密码
     * @return 执行结果
     */
    public boolean updatePassword(@Param("id")int id,@Param("password")String password);

    /**
     * 修改邮箱信息
     * @param id 待修改用户的id
     * @param email 新邮箱
     * @return 执行结果
     */
    public boolean updateEmail(@Param("id")int id,@Param("email")String email);
}
