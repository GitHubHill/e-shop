package com.hill.eshop.mapper;


import com.hill.eshop.model.User;

import java.util.List;

public interface UserMapper {

    List<User> queryAllUsers();

    int insertUser(User user);
}
