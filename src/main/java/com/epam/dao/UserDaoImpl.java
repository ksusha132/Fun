package com.epam.dao;

import com.epam.helper.UserHelper;
import com.epam.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(UserModel user) {
        String qr = "INSERT INTO person (id, name, email, birthbay) VALUES (?,?,?,?)";
        jdbcTemplate.update(qr, UserHelper.createID(), user.getName(), user.getEmail(), user.getBirthday());
    }
}
