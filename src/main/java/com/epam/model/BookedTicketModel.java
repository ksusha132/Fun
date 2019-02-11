package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class BookedTicketModel {
    private Integer idEvent;
    private Double price;
    private Date dateAndTimeEvent;
    private String auditoriumName;
    private Integer seat;

}
