package com.epam.service.impl;

import com.epam.discountStrategies.DiscountCounter;
import com.epam.model.EventModel;
import com.epam.model.UserModel;
import com.epam.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private List<DiscountCounter> strategies;

    @Autowired
    public void setStrategies(List<DiscountCounter> strategies) {
        this.strategies = strategies;
    }

    @Override
    public Integer getDiscount(UserModel user, EventModel event, LocalDateTime dateTime, Integer numberOfTickets) {
        return null;
    }
}
