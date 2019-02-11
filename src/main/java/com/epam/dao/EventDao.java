package com.epam.dao;

import com.epam.model.EventModel;

import java.util.Date;
import java.util.List;

public interface EventDao {
    void saveEvent(EventModel eventModel);

    void removeEvent(Integer id);

    List<EventModel> getAllEvents();

    EventModel getById(Integer id);

    EventModel getByName(String name);

    List<EventModel> getForDates(Date from, Date to);

}
