package com.epam.controller;

import com.epam.dto.BookTicketDto;
import com.epam.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController {

    @Autowired
    private BookingService bookingService;

    @GetMapping(value = "/getPrice")
    public ModelAndView getTickets() throws EmptyResultDataAccessException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tickets");
        return modelAndView;
    }

    @GetMapping(value = "/purchasedTickets/{event}/{dateTime}")
    public ModelAndView getPurchasedTickets(@PathVariable String event, @PathVariable String dateTime) throws EmptyResultDataAccessException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); //todo make it with AOP

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ticketObjects",
                bookingService.getPurchasedTicketsForEvent(event, LocalDateTime.parse(dateTime, formatter)));
        modelAndView.setViewName("tickets");
        return modelAndView;
    }


    @GetMapping(value = "/getNumberOfBookedTicked")
    public ModelAndView getNumberBookedTicked(@RequestParam String event, @RequestParam String dateTime) throws EmptyResultDataAccessException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ticketInfo",
                bookingService.getNumbersOfBookedTickets(event, LocalDateTime.parse(dateTime, formatter)));
        modelAndView.setViewName("tickets");
        return modelAndView;
    }


    @GetMapping(value = "/book")
    public ModelAndView bookTicket() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bookTickets");
        return modelAndView;
    }

    @PostMapping(value = "/book")
    public ModelAndView bookTicket(@RequestParam String name, @RequestParam String dateTime,
                                   @RequestParam Integer seat, @RequestParam String price,
                                   @RequestParam Boolean paid, ModelAndView modelAndView) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        BookTicketDto bookTicketDto = new BookTicketDto();
        bookTicketDto.setSeat(seat);
        bookTicketDto.setEventName(name);
        bookTicketDto.setDateTime(LocalDateTime.parse(dateTime, formatter));
        bookTicketDto.setPrice(Double.valueOf(price));
        bookTicketDto.setPaid(paid);
        bookingService.bookTickets(bookTicketDto);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
