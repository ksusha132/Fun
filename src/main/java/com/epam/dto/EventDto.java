package com.epam.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDto {
    private Long id;
    private String name;
    private String rating;
    private double basePrice;
    private String auditoriumName;
    private String datesEvent;
}
