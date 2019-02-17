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
import java.util.List;
import java.util.stream.Collectors;

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
    public Integer getNumbersOfBookedTickets(String event, LocalDateTime dateTime) {
        return ticketDao.getNumbersOfBookedTickets(event, dateTime);
    }

    @Override
    public void bookTickets(BookTicketDto ticket) {
        BookTicketModel bookTicketModel = new BookTicketModel();
        BeanUtils.copyProperties(ticket, bookTicketModel);
        ticketDao.bookTicket(bookTicketModel);
    }

    @Override
    public List<BookTicketDto> getPurchasedTicketsForEvent(String event, LocalDateTime dateTime) {
        List<BookTicketModel> bookTicketModels = ticketDao.getPurchTicketsForEvent(event, dateTime);
        return collectBookedTickets(bookTicketModels);
    }

    @Override
    public List<BookTicketDto> getUsersTickets(Integer idUser, String event, LocalDateTime dateTime) {
        List<BookTicketModel> bookTicketModels = ticketDao.getUsersTickets(idUser, event, dateTime);
        return collectBookedTickets(bookTicketModels);
    }

    private List<BookTicketDto> collectBookedTickets(List<BookTicketModel> bookTicketModels) {
        return bookTicketModels.stream()
                .filter(BookTicketModel::getPaid)
                .map(this::getBookTicketDto)
                .collect(Collectors.toList());
    }

    private BookTicketDto getBookTicketDto(BookTicketModel bookTicketModel) {
        BookTicketDto bookTicketDto = new BookTicketDto();
        BeanUtils.copyProperties(bookTicketModel, bookTicketDto);
        return bookTicketDto;
    }
}

//todo add AOP
//todo create more kind of exceptions
//todo watch videos
//todo and create loading script (import SQL)