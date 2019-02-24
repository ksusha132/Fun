package com.epam.service.impl;

import com.epam.discountStrategies.DiscountCounter;
import com.epam.dto.EventDto;
import com.epam.dto.UserDto;
import com.epam.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    private List<DiscountCounter> strategies;

    @Autowired
    public void setStrategies(List<DiscountCounter> strategies) {
        this.strategies = strategies;
    }

    @Override
    public Integer getDiscount(UserDto user, EventDto event, LocalDateTime dateTime, Integer numberOfTickets) {
        // check if  every discount available if 3 of them - get the higher one
        return Optional.of(strategies.stream()
                .mapToInt(strategy -> strategy.countDiscount(user, event, dateTime, numberOfTickets))
                .max()
                .getAsInt())
                .orElse(0);
    }
}
