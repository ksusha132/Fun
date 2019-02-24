package com.epam.controller;

import com.epam.service.EventService;
import com.epam.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.epam.file.FileParseHelper.parseEvents;
import static com.epam.file.FileParseHelper.usersSaver;

@Controller
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @PostMapping(value = "/parse")
    public String getDocuments(@RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {

            File convertedFile = new File(file.getOriginalFilename());
            file.transferTo(convertedFile);
            JSONParser jsonParser = new JSONParser();

            try {
                Object obj = jsonParser.parse(new FileReader(convertedFile));
                JSONObject jsonObject = (JSONObject) obj;

                JSONArray usersArray = (JSONArray) jsonObject.get("Users");
                JSONArray eventsArray = (JSONArray) jsonObject.get("Events");

                usersSaver(usersArray);
                parseEvents(eventsArray);

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        }
        return "index";
    }
}
