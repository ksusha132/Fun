package com.epam.service;

import com.epam.dto.UserDTO;

import java.util.List;

public interface UserService {
    void registerUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    void delete(Integer id);

    UserDTO getUserById(Integer id);

    UserDTO getUserByEmail(String email);
}
