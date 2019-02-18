package com.epam.service;

import com.epam.dto.BookTicketDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    Double getTicketPrice(String event, LocalDateTime dateTime, String user, Integer seat) throws Throwable;

    void bookTickets(BookTicketDto ticket);

    List<BookTicketDto> getPurchasedTicketsForEvent(String event, LocalDateTime dateTime);

    Integer getNumbersOfBookedTickets(String event, LocalDateTime dateTime);

    List<BookTicketDto> getUsersTickets(Integer idUser, String event, LocalDateTime dateTime);
}
