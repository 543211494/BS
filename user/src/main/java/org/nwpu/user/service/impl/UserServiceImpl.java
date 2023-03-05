package org.nwpu.user.service.impl;

import org.nwpu.user.bean.User;
import org.nwpu.user.mapper.UserMapper;
import org.nwpu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务接口实现类
 * @author lzy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User searchUserByUserName(String userName) {
        return userMapper.searchUserByUserName(userName);
    }

    @Override
    public User searchUserById(Integer userId){
        return userMapper.searchUserById(userId);
    }

    @Override
    public boolean updatePassword(Integer id, String password) {
        return userMapper.updatePassword(id, password);
    }

    @Override
    public boolean updateEmail(Integer id, String email) {
        return userMapper.updateEmail(id, email);
    }

    @Override
    public boolean resetPassword(Integer userId) {
        return userMapper.resetPassword(userId);
    }

    @Override
    public boolean deleteUserById(Integer userId) {
        return userMapper.deleteUserById(userId);
    }

    @Override
    public boolean insertUser(User user, Integer mid) {
        return userMapper.insertUser(user, mid);
    }
}
