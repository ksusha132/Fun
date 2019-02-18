package com.epam.discountStrategies;

import com.epam.dto.EventDto;
import com.epam.dto.UserDto;

import java.time.LocalDateTime;

public interface DiscountCounter {
    Integer countDiscount(UserDto user, EventDto event, LocalDateTime airDateTime, Integer numberOfTickets);
}
