package com.epam.discountStrategies;

import com.epam.model.EventModel;
import com.epam.model.UserModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("everyTenTicketStrategy")
public class EveryTenTicketStrategy implements DiscountCounter {
    //Every 10th ticket - give 50% for every 10th ticket purchased by user.
    // This strategy can also be applied for not-registered users if 10 or more tickets are bought

    @Override
    public Integer countDiscount(UserModel user, EventModel event, LocalDateTime airDateTime, Integer numberOfTickets) {
        if (user != null || numberOfTickets > 10) {
            double price = event.getBasePrice();
            long discountTicket = numberOfTickets / 10;
            double discountPrice = ((numberOfTickets * price) - (discountTicket * price * 0.5));
            double regularPrice = numberOfTickets * price;
            return (int) Math.round(((regularPrice - discountPrice) / regularPrice) * 100);
        }
        return 0;
    }
}
