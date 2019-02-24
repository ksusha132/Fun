package com.epam.service;

import com.epam.dto.EventDto;
import com.epam.dto.UserDto;

import java.time.LocalDateTime;

public interface DiscountService {
    Integer getDiscount(UserDto user, EventDto event, LocalDateTime dateTime, Integer numberOfTickets);
}
