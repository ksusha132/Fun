package com.epam.dao;

import com.epam.model.UserModel;

import java.util.List;

public interface UserDao {
    void save(UserModel userModel);

    List<UserModel> getAllUsers();
}
