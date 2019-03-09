package com.epam.service.impl;

import com.epam.dao.UserDao;
import com.epam.dto.UserDto;
import com.epam.model.UserModel;
import com.epam.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
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

    private UserDto getBy(Object o, Function<Object, UserModel> getValueFromDao) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(getValueFromDao.apply(o), userDto);
        return userDto;
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
        return getBy(id, appliedValue -> userDao.getById((Integer) appliedValue));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return getBy(email, appliedValue -> userDao.getByEmail((String) appliedValue));
    }

    @Override
    @Transactional
    public void changeCountMoney(String email, Double money) {
        userDao.changeCountMoney(email, money);
    }

    @Override
    public void updateBalance(String email, Double money) {
        Optional<UserModel> user = Optional.ofNullable(userDao.getByEmail(email));
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("There is no user");
        }
        // todo think of -

        Double balance = user.get().getBalance();
        Double newBalance = balance + money;

        changeCountMoney(email, newBalance);
    }
}
