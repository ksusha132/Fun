package com.epam.service;

import com.epam.dto.EventDto;

import java.util.Date;
import java.util.List;

public interface EventService {
    void save(EventDto eventDto);

    void remove(Integer id);

    EventDto getById(Integer id);

    EventDto getByName(String name);

    List<EventDto> getAllEvents();

    List<EventDto> getForDates(Date from, Date to);
}
