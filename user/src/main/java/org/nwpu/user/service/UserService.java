package org.nwpu.user.service;

import org.nwpu.user.bean.User;

/**
 * 用户服务接口
 * @author lzy
 */
public interface UserService {

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 查询结果
     */
    public User searchUserByUserName(String userName);
}
