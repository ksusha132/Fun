package com.epam.file;

import com.epam.dto.EventDto;
import com.epam.dto.UserDto;
import com.epam.service.EventService;
import com.epam.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FileParseHelper {

    static private UserService userService;
    static private EventService eventService;

    @Autowired
    public FileParseHelper(UserService userService, EventService eventService) {
        FileParseHelper.userService = userService;
        FileParseHelper.eventService = eventService;
    }

    public static void usersSaver(JSONArray jsonArray) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Object aJsonArray : jsonArray) {
            UserDto userDto = new UserDto();

            userDto.setName((String) ((JSONObject) aJsonArray).get("name"));
            userDto.setEmail((String) ((JSONObject) aJsonArray).get("email"));
            userDto.setRole((String) ((JSONObject) aJsonArray).get("role"));

            String birthday = String.valueOf(((JSONObject) aJsonArray).get("birthday"));

            userDto.setBirthday(LocalDate.parse(birthday, formatter));

            userService.registerUser(userDto);
        }
    }

    public static void parseEvents(JSONArray jsonArray) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Object aJsonArray : jsonArray) {

            EventDto eventDto = new EventDto();

            eventDto.setName((String) ((JSONObject) aJsonArray).get("name"));
            eventDto.setRating((String) ((JSONObject) aJsonArray).get("rating"));
            String basePrice = (String) ((JSONObject) aJsonArray).get("basePrice");

            eventDto.setBasePrice(Double.parseDouble(basePrice));

            eventDto.setAuditoriumName((String) ((JSONObject) aJsonArray).get("auditoriumName"));

            String datesEvent = String.valueOf(((JSONObject) aJsonArray).get("datesEvent"));

            eventDto.setDatesEvent(LocalDateTime.parse(datesEvent, formatter));

            eventService.save(eventDto);
        }
    }
}
