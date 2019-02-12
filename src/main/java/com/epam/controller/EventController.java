package com.epam.controller;

import com.epam.dto.EventDto;
import com.epam.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/event")
public class EventController {
    @Autowired
    private EventService eventService;


    @RequestMapping(value = "/getEvents", method = RequestMethod.GET)
    public ModelAndView getAllUsers(ModelAndView modelAndView) {
        modelAndView.addObject("events", eventService.getAllEvents());
        modelAndView.setViewName("events");
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView registerUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createEvent");
        return modelAndView;
    }

    @PostMapping(value = "/create")
    public ModelAndView registerUser(@RequestParam String name, @RequestParam String rating,
                                     @RequestParam Double basePrice, @RequestParam String auditName,
                                     @RequestParam String dates, ModelAndView modelAndView) {
        EventDto eventDto = new EventDto();
        eventDto.setName(name);
        eventDto.setBasePrice(basePrice);
        eventDto.setRating(rating);
        eventDto.setAuditoriumName(auditName);
        eventDto.setDatesEvent(dates);
        eventService.save(eventDto); // list
        modelAndView.setViewName("events");
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Integer id) {
        eventService.remove(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("events");
        return modelAndView;
    }

    @GetMapping(value = "/getEvent/{id}")
    public ModelAndView getUser(@PathVariable Integer id) throws EmptyResultDataAccessException {
        ModelAndView modelAndView = new ModelAndView();
        EventDto eventDto = eventService.getById(id);
        modelAndView.addObject("event", eventDto);
        modelAndView.setViewName("event");
        return modelAndView;
    }

    @GetMapping(value = "/getEventByName/{name}")
    public ModelAndView getUser(@PathVariable("name") String name) throws EmptyResultDataAccessException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("event", eventService.getByName(name));
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
