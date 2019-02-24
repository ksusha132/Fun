package com.epam.dao;

import com.epam.model.EventModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventDao {
    void saveEvent(EventModel eventModel);

    void removeEvent(Integer id);

    List<EventModel> getAllEvents();

    EventModel getById(Integer id);

    EventModel getByName(String name);

    EventModel getByNameAndTime(String name, LocalDateTime dateTime);

    List<EventModel> getForDates(Date from, Date to);
}
