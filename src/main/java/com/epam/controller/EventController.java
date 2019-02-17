package com.epam.controller;

import com.epam.dto.EventDto;
import com.epam.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/event")
public class EventController {
    @Autowired
    private EventService eventService;


    @GetMapping(value = "/getEvents")
    public ModelAndView getAllEvents(ModelAndView modelAndView) {
        modelAndView.addObject("eventObjects", eventService.getAllEvents());
        modelAndView.setViewName("events");
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView createEvent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("eventCreate");
        return modelAndView;
    }

    @PostMapping(value = "/create")
    @Description("only for admins")
    public ModelAndView registerUser(@RequestParam String name, @RequestParam String rating,
                                     @RequestParam String basePrice, @RequestParam String auditName,
                                     @RequestParam String dates, ModelAndView modelAndView) {
        EventDto eventDto = new EventDto();
        eventDto.setName(name);
        eventDto.setRating(rating);
        eventDto.setBasePrice(Double.parseDouble(basePrice));
        eventDto.setAuditoriumName(auditName);
        eventDto.setDatesEvent(dates);
        eventService.save(eventDto);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    @Description("only for admins")
    public ModelAndView deleteUser(@PathVariable Integer id) {
        eventService.remove(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("events");
        return modelAndView;
    }

    @GetMapping(value = "/getEvent/{id}")
    @Description("only for admins")
    public ModelAndView getUser(@PathVariable Integer id) throws EmptyResultDataAccessException {
        ModelAndView modelAndView = new ModelAndView();
        EventDto eventDto = eventService.getById(id);
        modelAndView.addObject("eventObject", eventDto);
        modelAndView.setViewName("event");
        return modelAndView;
    }

    @GetMapping(value = "/getEventByName/{name}")
    public ModelAndView getUser(@PathVariable("name") String name) throws EmptyResultDataAccessException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("eventObject", eventService.getByName(name));
        modelAndView.setViewName("event");
        return modelAndView;
    }

    @PostMapping(value = "/getTickets/{from}/{to}")
    public ModelAndView registerUser(@PathVariable("from") String from, @PathVariable("to") String to,
                                     ModelAndView modelAndView) {
        //eventService.getForDates(from, to);
        modelAndView.setViewName("events");
        return modelAndView;
    }
}
