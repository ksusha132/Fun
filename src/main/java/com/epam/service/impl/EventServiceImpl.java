package com.epam.service.impl;

import com.epam.dao.EventDao;
import com.epam.dto.EventDto;
import com.epam.model.EventModel;
import com.epam.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public void save(EventDto eventDto) {
        EventModel eventModel = new EventModel();
        eventModel.setName(eventDto.getName());
        eventModel.setRating(eventDto.getRating());
        eventModel.setAuditoriumName(eventDto.getAuditoriumName());
        eventModel.setBasePrice(eventDto.getBasePrice());
        eventModel.setAuditoriumString(eventDto.getDatesEvent());
        eventDao.saveEvent(eventModel);
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public EventDto getById(Integer id) {
        return null;
    }

    @Override
    public EventDto getByName(String name) {
        return null;
    }

    @Override
    public List<EventDto> getAllEvents() {
        return null;
    }

    @Override
    public List<EventDto> getForDates(Date from, Date to) {
        return null;
    }
}