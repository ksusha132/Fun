package com.epam.controller;

import com.epam.dto.UserDto;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ModelAndView getAllUsers(ModelAndView modelAndView) {
        modelAndView.addObject("userObjects", userService.getAllUsers());
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping(value = "/register")
    public ModelAndView registerUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userRegister");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public ModelAndView registerUser(@RequestParam String name, @RequestParam String email,
                                     @RequestParam String birthday, ModelAndView modelAndView) {
        UserDto userDto = new UserDto();
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setBirthday(LocalDate.parse(birthday));
        userDto.setRole("USER_ROLE");
        userService.registerUser(userDto);
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping(value = "/getUser/{id}")
    public ModelAndView getUser(@PathVariable Integer id) throws EmptyResultDataAccessException {
        ModelAndView modelAndView = new ModelAndView();
        UserDto userDto = userService.getUserById(id);
        modelAndView.addObject("user", userDto);
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @GetMapping(value = "/getUserByEmail/{email}")
    public ModelAndView getUser(@PathVariable("email") String email) throws EmptyResultDataAccessException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.getUserByEmail(email));
        modelAndView.setViewName("user");
        return modelAndView;
    }
}
