package com.epam.dao.impl;

import com.epam.dao.EventDao;
import com.epam.helper.UtilHelper;
import com.epam.model.EventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository("EventDao")
public class EventDaoImpl implements EventDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveEvent(EventModel eventModel) {

        String qr = "INSERT INTO event (id, name, rating, base_price, dates_event, auditorium_name) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(qr, UtilHelper.createID(), eventModel.getName(), eventModel.getRating(), eventModel.getBasePrice(),
                Timestamp.valueOf(eventModel.getDatesEvent()), eventModel.getAuditoriumName());
    }

    @Override
    public void removeEvent(Integer id) {
        String SQL = "DELETE FROM event WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public List<EventModel> getAllEvents() {
        String SQL = "SELECT * FROM event";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(EventModel.class));
    }

    @Override
    public EventModel getById(Integer id) {
        String SQL = "SELECT * FROM event WHERE id = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(EventModel.class));
    }

    @Override
    public EventModel getByName(String name) {
        String SQL = "SELECT * FROM event WHERE name = ? ";
        List<EventModel> eventModels = jdbcTemplate.query(SQL, new Object[]{name}, new BeanPropertyRowMapper<>(EventModel.class));
        return eventModels.stream().findFirst().get();
    }

    @Override
    public List<EventModel> getForDates(Date from, Date to) {
        String SQL = "SELECT * FROM event";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(EventModel.class));
    }

    @Override
    public EventModel getByNameAndTime(String name, LocalDateTime dateTime) {
        String SQL = "SELECT * FROM event WHERE name = ? AND dates_event = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{name, Timestamp.valueOf(dateTime)}, new BeanPropertyRowMapper<>(EventModel.class));
    }
}
