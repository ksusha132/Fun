package com.epam.dao.impl;

import com.epam.dao.TicketDao;
import com.epam.helper.UtilHelper;
import com.epam.model.BookTicketModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("TicketDao")
public class BookTicketDaoImpl implements TicketDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void bookTicket(BookTicketModel model) {
        String qr = "INSERT INTO ticket (id, date_time, seat, event_name, price, paid) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(qr, UtilHelper.createID(), Timestamp.valueOf(model.getDateTime()),
                model.getSeat(), model.getEventName(), model.getPrice(), model.getPaid());
    }

    @Override
    public List<BookTicketModel> getPurchTicketsForEvent(String eventName, LocalDateTime dateTime) {
        String SQL = "SELECT * FROM ticket WHERE event_name = ? AND date_time = ?";
        return jdbcTemplate.query(SQL, new Object[]{eventName, Timestamp.valueOf(dateTime)}, new BeanPropertyRowMapper<>(BookTicketModel.class));
    }

    @Override
    public Integer getNumbersOfBookedTickets(String eventName, LocalDateTime dateTime) {
        String SQL = "SELECT COUNT (*) FROM ticket WHERE event_name = ? AND date_time = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{eventName, Timestamp.valueOf(dateTime)}, Integer.class);
    }
}
