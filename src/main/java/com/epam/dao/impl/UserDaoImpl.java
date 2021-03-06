package com.epam.dao.impl;

import com.epam.dao.UserDao;
import com.epam.helper.UtilHelper;
import com.epam.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(UserModel user) {
        String qr = "INSERT INTO person (id, name, email, birthday, role) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(qr, UtilHelper.createID(), user.getName(), user.getEmail(),
                Timestamp.valueOf(user.getBirthday().atStartOfDay()), user.getRole());
    }

    @Override
    public List<UserModel> getAllUsers() {
        String SQL = "SELECT * FROM person";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(UserModel.class));
    }

    @Override
    public void deleteUser(Integer id) {
        String SQL = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public UserModel getById(Integer id) {
        String SQL = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(UserModel.class));
    }

    @Override
    public UserModel getByEmail(String email) {
        String SQL = "SELECT * FROM person WHERE email = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{email}, new BeanPropertyRowMapper<>(UserModel.class));
    }
}
