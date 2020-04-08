package com.jycz.user.service.impl;

import com.jycz.common.dao.UserMapper;
import com.jycz.common.model.entity.User;
import com.jycz.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ling
 * @data 2020/4/8 11:29
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean usernameIsSave(String username) {
        User user = userMapper.selectByUsername(username);
        return user != null;
    }
}
