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
     * 根据用户id查找用户
     * @param userId 用户id
     * @return
     */
    public User searchUserById(Integer userId);

    /**
     * 修改密码
     * @param id 待修改用户的id
     * @param password 新密码
     * @return 执行结果
     */
    public boolean updatePassword(Integer id,String password);

    /**
     * 修改邮箱信息
     * @param id 待修改用户的id
     * @param email 新邮箱
     * @return 执行结果
     */
    public boolean updateEmail(Integer id,String email);

    /**
     * 重置用户密码
     * @param userId 用户id
     * @return 执行结果
     */
    public boolean resetPassword(Integer userId);

    /**
     * 根据用户id删除用户
     * @param userId 用户id
     * @return
     */
    public boolean deleteUserById(Integer userId);

    /**
     * 新增用户
     * @param user 用户对象
     * @param mid 用户专业id
     * @return
     */
    public boolean insertUser(User user,Integer mid);
}
