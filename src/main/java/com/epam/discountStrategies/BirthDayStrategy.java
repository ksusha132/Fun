package com.epam.discountStrategies;

import com.epam.dto.EventDto;
import com.epam.dto.UserDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("birthDayStrategy")
public class BirthDayStrategy implements DiscountCounter {
    //Birthday strategy - give 7% if user has birthday within 5 days of air date

    @Override
    public Integer countDiscount(UserDto user, EventDto event, LocalDateTime airDateTime, Integer numberOfTickets) {
        //return isDiscountAvailable(airDateTime, user) ? 7 : 0;
        return null;
    }

//    private Boolean isDiscountAvailable(LocalDateTime airDateTime, UserDto user) {
//        if (user == null) {
//            return false;
//        }
//        int birthDay = Optional.ofNullable(user.getBirthday())
//                .map(Date::getDayOfYear)
//                .orElse(0);
//        int airDate = airDateTime.toLocalDate().getDayOfYear();
//        return (birthDay > airDate - 5) && (birthDay < birthDay + 5);
//
//    }
}
