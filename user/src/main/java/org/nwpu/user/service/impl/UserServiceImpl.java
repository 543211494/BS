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
    public boolean updatePassword(int id, String password) {
        return userMapper.updatePassword(id, password);
    }

    @Override
    public boolean updateEmail(int id, String email) {
        return userMapper.updateEmail(id, email);
    }
}
