package com.epam.controller;

import com.epam.dto.UserDTO;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/document")
public class PdfController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/generate/{docName}")
    public String getDocuments(Model model, @PathVariable String docName) {
        List<UserDTO> userDTOS = userService.getAllUsers();
        model.addAttribute("usersPdf", userDTOS);
        return "pdfUsers";
    }
}
