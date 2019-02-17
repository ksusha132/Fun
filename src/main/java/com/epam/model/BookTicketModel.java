package com.epam.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookTicketModel {
    private LocalDateTime dateTime;
    private Integer seat;
    private String eventName;
    private Double price;
    private Boolean paid;
}
