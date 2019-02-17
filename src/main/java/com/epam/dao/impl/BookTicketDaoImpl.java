package com.epam.dao.impl;

import com.epam.dao.TicketDao;
import com.epam.helper.UtilHelper;
import com.epam.model.BookTicketModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository("TicketDao")
public class BookTicketDaoImpl implements TicketDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void bookTicket(BookTicketModel model) {
        String qr = "INSERT INTO ticket (id, date_time, seat, event_name, price, paid) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(qr, UtilHelper.createID(), Timestamp.valueOf(model.getTime()),
                model.getSeat(), model.getEvent(), model.getPrice(), model.getPaid());
    }
}
