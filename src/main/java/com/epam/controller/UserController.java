package com.epam.controller;

import com.epam.dto.UserDTO;
import com.epam.exception.ResourceNotFoundException;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userObjects", userService.getAllUsers());
        modelAndView.setViewName("users");
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


    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping(value = "/getUser/{id}")
    public ModelAndView getUser(@PathVariable Integer id) {
        try {
            ModelAndView modelAndView = new ModelAndView();
            UserDTO userDTO = userService.getUserById(id);
            modelAndView.addObject("user", userDTO);
            modelAndView.setViewName("user");
            return modelAndView;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @GetMapping(value = "/getUserByEmail/{email}")
    public ModelAndView getUser(@PathVariable("email") String email) {
        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", userService.getUserByEmail(email));
            modelAndView.setViewName("user");
            return modelAndView;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(email);
        }
    }
}
