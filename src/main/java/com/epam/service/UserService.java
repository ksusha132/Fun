package com.epam.service;

import com.epam.dto.UserDto;

import java.util.List;

public interface UserService {
    void registerUser(UserDto userDto);

    List<UserDto> getAllUsers();

    void delete(Integer id);

    UserDto getUserById(Integer id);

    UserDto getUserByEmail(String email);

    void changeCountMoney(String email, Double bigDecimal);

    void updateBalance(String email, Double bigDecimal);
}
