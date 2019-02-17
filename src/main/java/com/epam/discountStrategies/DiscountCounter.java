package com.epam.discountStrategies;

import com.epam.model.EventModel;
import com.epam.model.UserModel;

import java.time.LocalDateTime;

public interface DiscountCounter {
    Integer countDiscount(UserModel user, EventModel event, LocalDateTime airDateTime, Integer numberOfTickets);
}
