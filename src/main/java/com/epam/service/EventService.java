package com.epam.service;

import com.epam.dto.EventDto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventService {
    void save(EventDto eventDto);

    void remove(Integer id);

    EventDto getById(Integer id);

    EventDto getByName(String name);

    EventDto getByNameAddTime(String name, LocalDateTime dateTime);

    List<EventDto> getAllEvents();

    List<EventDto> getForDates(Date from, Date to);
}
