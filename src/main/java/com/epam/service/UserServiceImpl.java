package com.epam.service;

import com.epam.converter.UserModelToUserDtoConverter;
import com.epam.dao.UserDao;
import com.epam.dto.UserDTO;
import com.epam.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void registerUser(UserDTO userDTO) {
        UserModel userModel = new UserModel();
        userModel.setName(userDTO.getName());
        userModel.setEmail(userDTO.getEmail());
        userModel.setBirthday(userDTO.getBirthday());
        userModel.setRole("USER_ROLE");
        userDao.save(userModel);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserModel> userModels = userDao.getAllUsers();
        List<UserDTO> userDTOList = new ArrayList<>();
        userModels.forEach(userModel -> {
            userDTOList.add(UserModelToUserDtoConverter.convert(userModel));
        });
        return userDTOList;
    }

    @Override
    public void delete(Integer id) {
        userDao.deleteUser(id);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return UserModelToUserDtoConverter.convert(userDao.getById(id));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return UserModelToUserDtoConverter.convert(userDao.getByEmail(email));
    }
}
