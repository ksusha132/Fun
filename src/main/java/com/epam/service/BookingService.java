package com.epam.service;

import com.epam.dto.BookTicketDto;
import com.epam.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    Double getTicketsPrice(String event, LocalDateTime dateTime, UserDto user, String seats) throws Throwable;

    void bookTickets(BookTicketDto ticket);

    List<BookTicketDto> getPurchasedTicketsForEvent(String event, LocalDateTime dateTime);

    Integer getNumbersOfBookedTickets(String event, LocalDateTime dateTime);
}
