package com.hill.eshop.service;


import com.hill.eshop.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers(int pageNum, int pageSize);

    int insertUser(User user);

    User getUser(User user);
}
