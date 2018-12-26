package com.hill.eshop.service.impl;

import com.github.pagehelper.PageHelper;
import com.hill.eshop.mapper.UserMapper;
import com.hill.eshop.model.User;
import com.hill.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserSericeImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return userMapper.getAllUsers();
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User getUser(User user) {
        return userMapper.getUser(user);
    }
}
