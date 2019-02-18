package com.epam.service.impl;

import com.epam.dao.TicketDao;
import com.epam.dto.BookTicketDto;
import com.epam.dto.EventDto;
import com.epam.dto.UserDto;
import com.epam.model.AuditoriumModel;
import com.epam.model.BookTicketModel;
import com.epam.service.*;
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

    @Autowired
    private DiscountService discountService;

    @Override
    public Double getTicketPrice(String event, LocalDateTime dateTime, String user, Integer seat) throws Throwable {
        UserDto userDto = userService.getUserByEmail(user);
        EventDto eventDto = eventService.getByNameAddTime(event, dateTime);
        AuditoriumModel auditoriumModel = auditoriumService.getByName(eventDto.getAuditoriumName()
                .replace(" ", ""));
        Double price = null;
        if (isVipSeat(seat, auditoriumModel)) {
            price = eventDto.getBasePrice() * 1.5 * getRaitCoff(eventDto);
        } else {
            price = eventDto.getBasePrice() * getRaitCoff(eventDto);
        }
        return price - (price * getDiscount(userDto, eventDto, dateTime, seat));
    }

    private Boolean isVipSeat(Integer seat, AuditoriumModel auditoriumModel) {
        return auditoriumModel.getVipSeats().contains(seat);
    }

    private Integer getDiscount(UserDto userDto, EventDto eventDto, LocalDateTime dateTime, Integer contSeats) {
        return discountService.getDiscount(userDto, eventDto, dateTime, contSeats);
    }

    private Double getRaitCoff(EventDto eventDto) {
        Double rait = null;
        switch (eventDto.getRating()) {
            case "high":
                rait = 1.25;
                break;
            case "mid":
                rait = 1.15;
                break;
            case "low":
                rait = 1.0;
                break;
        }
        return rait;
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