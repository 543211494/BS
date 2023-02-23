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

    /**
     * 修改密码
     * @param id 待修改用户的id
     * @param password 新密码
     * @return 执行结果
     */
    public boolean updatePassword(int id,String password);

    /**
     * 修改邮箱信息
     * @param id 待修改用户的id
     * @param email 新邮箱
     * @return 执行结果
     */
    public boolean updateEmail(int id,String email);
}
