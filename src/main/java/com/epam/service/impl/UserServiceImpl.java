package com.epam.service.impl;

import com.epam.dao.UserDao;
import com.epam.dto.UserDto;
import com.epam.model.UserModel;
import com.epam.service.UserService;
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
    public void registerUser(UserDto userDto) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userDao.save(userModel);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserModel> userModels = userDao.getAllUsers();
        return userModels.stream()
                .map(this::convertUserDto)
                .collect(Collectors.toList());
    }

    private UserDto convertUserDto(UserModel userModel) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userModel, userDto);
        return userDto;
    }

    @Override
    public void delete(Integer id) {
        userDao.deleteUser(id);
    }

    @Override
    public UserDto getUserById(Integer id) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDao.getById(id), userDto);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDao.getByEmail(email), userDto);
        return userDto;
    }
}
