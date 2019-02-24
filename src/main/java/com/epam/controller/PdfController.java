package com.epam.controller;

import com.epam.dto.BookTicketDto;
import com.epam.dto.EventDto;
import com.epam.dto.TicketDTO;
import com.epam.dto.UserDto;
import com.epam.service.BookingService;
import com.epam.service.EventService;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(value = "/document")
public class PdfController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;

    @GetMapping(value = "/generate/{docName}")
    public String getDocuments(Model model, @PathVariable String docName) {
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("usersPdf", userDtos);
        return "pdfUsers";
    }

    @GetMapping(value = "/userReceipt/{event}/{user}/{dateTime}/{docName}")
    public String createReceipt(Model model, @PathVariable String event, @PathVariable Integer user,
                                @PathVariable String dateTime, @PathVariable String docName) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        List<BookTicketDto> bookTicketDtos = bookingService.getUsersTickets(user, event, LocalDateTime.parse(dateTime, formatter));
        UserDto userDto = userService.getUserById(bookTicketDtos.stream().map(BookTicketDto::getIdUser).findFirst().get());
        EventDto eventDto = eventService.getByName(bookTicketDtos.stream().map(BookTicketDto::getEventName).findFirst().get());

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setAuditorium(eventDto.getAuditoriumName());
        ticketDTO.setUserName(userDto.getName());
        ticketDTO.setBookTicketDtos(bookTicketDtos);

        model.addAttribute("ticketPdf", ticketDTO);
        return "pdfTickets";
    }
}
