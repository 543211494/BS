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

    public User searchUserByUserName(String userName) {
        return userMapper.searchUserByUserName(userName);
    }
}
