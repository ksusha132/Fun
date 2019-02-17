package com.epam.discountStrategies;

import com.epam.model.EventModel;
import com.epam.model.UserModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component("newYearStrategy")
public class NewYearStrategy implements DiscountCounter {
    //NewYear strategy - give 70%  within 5 days of air date

    @Override
    public Integer countDiscount(UserModel user, EventModel event, LocalDateTime airDateTime, Integer numberOfTickets) {
        return isDiscountAvailable(airDateTime, user) ? 70 : 0;
    }

    private Boolean isDiscountAvailable(LocalDateTime airDateTime, UserModel user) {
        if (user == null) {
            return false;
        }
        LocalDate newYear = LocalDate.ofYearDay(2018, 365);
        LocalDate airDate = airDateTime.toLocalDate();
        return newYear.isAfter(airDate.minusDays(5)) && newYear.isBefore(airDate.plusDays(5));

    }

}
