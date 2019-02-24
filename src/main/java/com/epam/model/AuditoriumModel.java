package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriumModel {
    private String name;
    private Integer numberOfSeats;
    private List<Integer> vipSeats;
}
