package com.epam.service.impl;

import com.epam.dao.EventDao;
import com.epam.dto.EventDto;
import com.epam.model.EventModel;
import com.epam.service.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public void save(EventDto eventDto) {
        EventModel eventModel = new EventModel();
        BeanUtils.copyProperties(eventDto, eventModel);
        eventDao.saveEvent(eventModel);
    }

    @Override
    public void remove(Integer id) {
        eventDao.removeEvent(id);
    }

    private EventDto getBy(Object o, Function<Object, EventModel> getValueFromDao) {
        EventDto eventDto = new EventDto();
        BeanUtils.copyProperties(getValueFromDao.apply(o), eventDto);
        return eventDto;
    }

    @Override
    public EventDto getById(Integer id) {
        return getBy(id, appliedValue -> eventDao.getById((Integer) appliedValue));
    }

    @Override
    public EventDto getByName(String name) {
        return getBy(name, appliedValue -> eventDao.getByName((String) appliedValue));
    }

    @Override
    public List<EventDto> getAllEvents() {
        List<EventModel> eventModels = eventDao.getAllEvents();
        return eventModels.stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    private EventDto convertDto(EventModel eventModel) {
        EventDto eventDto = new EventDto();
        BeanUtils.copyProperties(eventModel, eventDto);
        return eventDto;
    }

    @Override
    public EventDto getByNameAddTime(String name, LocalDateTime dateTime) {
        EventModel eventModel = eventDao.getByNameAndTime(name, dateTime);
        return convertDto(eventModel);
    }

    @Override
    public List<EventDto> getForDates(Date from, Date to) {
        return null;
    }
}
