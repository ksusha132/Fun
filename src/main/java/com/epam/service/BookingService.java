package com.epam.service;

import com.epam.dto.EventDto;
import com.epam.dto.UserDto;

import java.time.LocalDateTime;
import java.util.Set;

public interface BookingService {
    Double getTicketsPrice(EventDto event, LocalDateTime dateTime, UserDto user, String seats) throws Throwable;
//
//    void bookTickets(BookedTicket ticket);
//
//    Set<BookedTicket> getPurchasedTicketsForEvent(EventDto event, LocalDateTime dateTime);

    Set<Integer> getNumbersOfBookedTickets(EventDto event, LocalDateTime dateTime);
}
