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
}
