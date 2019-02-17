package com.epam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketDTO {
    private List<BookTicketDto> bookTicketDtos;
    private String userName;
    private String auditorium;
}
