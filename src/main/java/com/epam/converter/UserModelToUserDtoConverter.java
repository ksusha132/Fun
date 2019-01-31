package com.epam.converter;

import com.epam.dto.UserDTO;
import com.epam.model.UserModel;

public class UserModelToUserDtoConverter {
    public static UserDTO convert(UserModel source) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(source.getName());
        userDTO.setEmail(source.getEmail());
        userDTO.setBirthday(source.getBirthday());
        return userDTO;
    }
}
