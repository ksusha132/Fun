package com.epam.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookTicketDto {
    private LocalDateTime time;
    private Integer seat;
    private String event;
    private Double price;
    private Boolean paid;
}
