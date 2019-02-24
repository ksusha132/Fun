package com.epam.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDto {
    private String name;
    private String rating;
    private double basePrice;
    private String auditoriumName;
    private LocalDateTime datesEvent;
}
