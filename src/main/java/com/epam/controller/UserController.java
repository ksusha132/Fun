package com.epam.controller;

import com.epam.dto.UserDTO;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ModelAndView getUsers() {
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setName("Victor");

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setName("Vasilii");

        ArrayList<UserDTO> userDTOS = new ArrayList<UserDTO>();
        userDTOS.add(userDTO1);
        userDTOS.add(userDTO2);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userObjects", userDTOS);
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @GetMapping(value = "/register")
    public ModelAndView registerUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registerUser");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public void registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String birthday) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setBirthday(java.sql.Date.valueOf(birthday));
        userService.registerUser(userDTO);
    }
}
