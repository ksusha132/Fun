package com.epam.service;

import com.epam.dao.UserDao;
import com.epam.dto.UserDTO;
import com.epam.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void registerUser(UserDTO userDTO) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        userDao.save(userModel);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserModel> userModels = userDao.getAllUsers();
        return userModels.stream()
                .map(this::convertUserDto)
                .collect(Collectors.toList());
    }

    private UserDTO convertUserDto(UserModel userModel) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userModel, userDTO);
        return userDTO;
    }

    @Override
    public void delete(Integer id) {
        userDao.deleteUser(id);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDao.getById(id), userDTO);
        return userDTO;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDao.getByEmail(email), userDTO);
        return userDTO;
    }
}
