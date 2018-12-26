package com.hill.eshop.mapper;


import com.hill.eshop.model.User;

import java.util.List;

public interface UserMapper {

    List<User> getAllUsers();

    User getUser(User user);

    int insertUser(User user);
}
