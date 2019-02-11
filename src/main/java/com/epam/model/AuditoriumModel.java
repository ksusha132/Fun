package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriumModel {
    private String name;
    private Integer numberOfSeats;
    private String vipSeats;
    private String seats;
}
