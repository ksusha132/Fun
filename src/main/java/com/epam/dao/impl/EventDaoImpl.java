package com.epam.dao.impl;

import com.epam.dao.EventDao;
import com.epam.model.EventModel;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("EventDao")
public class EventDaoImpl implements EventDao {
    @Override
    public void saveEvent(EventModel eventModel) {

    }

    @Override
    public void removeEvent(Integer id) {

    }

    @Override
    public List<EventModel> getAllEvents() {
        return null;
    }

    @Override
    public EventModel getById(Integer id) {
        return null;
    }

    @Override
    public EventModel getByName(String name) {
        return null;
    }

    @Override
    public List<EventModel> getForDates(Date from, Date to) {
        return null;
    }
}
