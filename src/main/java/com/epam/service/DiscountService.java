package com.epam.service;

import com.epam.model.EventModel;
import com.epam.model.UserModel;

import java.time.LocalDateTime;

public interface DiscountService {
    Integer getDiscount(UserModel user, EventModel event, LocalDateTime dateTime, Integer numberOfTickets);
}
