package com.epam.discountStrategies;

import com.epam.model.EventModel;
import com.epam.model.UserModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component("birthDayStrategy")
public class BirthDayStrategy implements DiscountCounter {
    //Birthday strategy - give 7% if user has birthday within 5 days of air date

    @Override
    public Integer countDiscount(UserModel user, EventModel event, LocalDateTime airDateTime, Integer numberOfTickets) {
        return isDiscountAvailable(airDateTime, user) ? 7 : 0;
    }

    private Boolean isDiscountAvailable(LocalDateTime airDateTime, UserModel user) {
        if (user == null) {
            return false;
        }
        int birthDay = Optional.ofNullable(user.getBirthday())
                .map(LocalDate::getDayOfYear)
                .orElse(0);
        int airDate = airDateTime.toLocalDate().getDayOfYear();
        return (birthDay >= airDate - 5) && (birthDay <= birthDay + 5);

    }
}
