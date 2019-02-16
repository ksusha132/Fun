package com.epam.dao.impl;

import com.epam.dao.EventDao;
import com.epam.model.EventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("EventDao")
public class EventDaoImpl implements EventDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveEvent(EventModel eventModel) {

        String qr = "INSERT INTO event (id, name, rating, base_price, date_time, auditorium) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(qr, eventModel.getId(), eventModel.getName(), eventModel.getRating(), eventModel.getBasePrice(),
                eventModel.getDatesString(), eventModel.getAuditoriumName());
    }

    @Override
    public void removeEvent(Integer id) {
        String SQL = "DELETE FROM event WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public List<EventModel> getAllEvents() {
        return null;
    }

    @Override
    public EventModel getById(Integer id) {
        String SQL = "SELECT * FROM event WHERE id = ?";
        return jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(EventModel.class));
    }

    @Override
    public EventModel getByName(String name) {
        String SQL = "SELECT * FROM event WHERE name = ?";
        return jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(EventModel.class));
    }

    @Override
    public List<EventModel> getForDates(Date from, Date to) {
        String SQL = "SELECT * FROM event";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(EventModel.class));
    }
}
