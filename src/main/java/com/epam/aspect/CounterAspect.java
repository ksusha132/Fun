package com.epam.aspect;

import com.epam.dao.CounterDao;
import com.epam.dto.BookTicketDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.epam.aspect.AopHelper.checkAndSet;

@Aspect
@Component
public class CounterAspect {

    private final CounterDao counterDao;

    @Autowired
    public CounterAspect(CounterDao counterDao) {
        this.counterDao = counterDao;
    }

    @Pointcut("execution(public * com.epam.service.BookingService.getTicketPrice(..))")
    private void getTicketsPrice() {
    }

    @Pointcut("execution(public * com.epam.service.EventService.getByName(String))")
    private void getEventByName() {
    }

    @Pointcut("execution(public * com.epam.service.BookingService.bookTickets(..))")
    private void bookTickets() {
    }


    @Before("getEventByName()")
    public void countBeforeGetByName(JoinPoint joinPoint) {
        String eventName = String.valueOf(joinPoint.getArgs()[0]);
        checkAndSet(eventName, "GET_BY_NAME");
    }

    @Before("getTicketsPrice()")
    public void countBeforeGetTicketPrice(JoinPoint joinPoint) {
        String eventName = String.valueOf(joinPoint.getArgs()[0]);
        checkAndSet(eventName, "GET_EVENT_PRICE");
    }

    @Before("bookTickets()")
    public void countBeforeBookTickets(JoinPoint joinPoint) {
        BookTicketDto bookedTicket = (BookTicketDto) joinPoint.getArgs()[0];
        String eventName = bookedTicket.getEventName();
        checkAndSet(eventName, "BOOK_TICKETS");
    }



}
