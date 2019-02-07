package com.epam.service;

import com.epam.dao.UserDao;
import com.epam.dto.UserDTO;
import com.epam.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

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
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userModel, userDTO);
            userDTOList.add(userDTO);
        });
        return userDTOList;
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
