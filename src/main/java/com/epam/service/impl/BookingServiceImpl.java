package com.epam.service.impl;

import com.epam.dao.TicketDao;
import com.epam.dto.BookTicketDto;
import com.epam.dto.EventDto;
import com.epam.dto.UserDto;
import com.epam.model.BookTicketModel;
import com.epam.service.AuditoriumService;
import com.epam.service.BookingService;
import com.epam.service.EventService;
import com.epam.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketDao ticketDao;

    @Override
    public Double getTicketsPrice(String event, LocalDateTime dateTime, UserDto user, String seats) throws Throwable {
        UserDto userDto = userService.getUserByEmail(user.getEmail()); // or current user do it after security functional implemented
        EventDto eventDto = eventService.getByName(event);
        if (dateTime.equals(eventDto.getDatesEvent())) {
            // if any seats left

            //get base price
            //get vip seats
            //get price with discount if available
        }

        return null;
    }

    @Override
    public Set<Integer> getNumbersOfBookedTickets(EventDto event, LocalDateTime dateTime) {
        return null;
    }

    @Override
    public void bookTickets(BookTicketDto ticket) {
        BookTicketModel bookTicketModel = new BookTicketModel();
        BeanUtils.copyProperties(ticket, bookTicketModel);
        ticketDao.bookTicket(bookTicketModel);
    }

    @Override
    public Set<BookTicketDto> getPurchasedTicketsForEvent(EventDto event, LocalDateTime dateTime) {
        return null;
    }
}

//todo add AOP
//todo create more kind of exceptions
//todo watch videos
//todo and create loading script (import SQL)