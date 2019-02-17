package com.epam.dao;

import com.epam.model.BookTicketModel;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketDao {
    void bookTicket(BookTicketModel model);

    List<BookTicketModel> getPurchTicketsForEvent(String eventName, LocalDateTime dateTime);

    Integer getNumbersOfBookedTickets(String eventName, LocalDateTime dateTime);

    List<BookTicketModel> getUsersTickets(Integer idUser, String event, LocalDateTime dateTime);
}
