package com.epam.dao.impl;

import com.epam.dao.CounterDao;
import com.epam.model.CounterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("CounterDao")
public class CounterDaoImpl implements CounterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(CounterModel counter) {
        String qr = "INSERT INTO counter (name, count_type, count) VALUES (?,?,?)";
        jdbcTemplate.update(qr, counter.getName(), counter.getCountType(), counter.getCount());
    }

    @Override
    public void update(CounterModel counter) {
        String qr = "UPDATE counter SET count = ? WHERE name = ? AND count_type = ?";
        jdbcTemplate.update(qr, counter.getCount(), counter.getName(), counter.getCountType());
    }

    @Override
    public CounterModel getByNameAndType(String name, String type) {
        try {
            String SQL = "SELECT * FROM counter WHERE name = ? AND count_type = ?";
            return jdbcTemplate.queryForObject(SQL, new Object[]{name, type}, new BeanPropertyRowMapper<>(CounterModel.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
