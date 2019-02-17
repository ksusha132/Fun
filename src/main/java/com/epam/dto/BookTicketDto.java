package com.epam.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookTicketDto {
    private LocalDateTime dateTime;
    private Integer seat;
    private String eventName;
    private Double price;
    private Boolean paid;
}
