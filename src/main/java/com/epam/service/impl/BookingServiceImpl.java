package com.epam.service.impl;

import com.epam.dto.EventDto;
import com.epam.dto.UserDto;
import com.epam.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {
    @Override
    public Double getTicketsPrice(EventDto event, LocalDateTime dateTime, UserDto user, String seats) throws Throwable {
        return null;
    }

    @Override
    public Set<Integer> getNumbersOfBookedTickets(EventDto event, LocalDateTime dateTime) {
        return null;
    }
}
