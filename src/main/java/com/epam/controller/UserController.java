package com.epam.controller;

import com.epam.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/user")
public class UserController
{
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ModelAndView getUsers() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("Victor");

        User user2 = new User();
        user2.setId(2);
        user2.setName("Vasilii");

        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userObjects", users);
        modelAndView.setViewName("user");
        return modelAndView;
    }
}
