package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Auditorium {
    private String name;
    private Integer numberOfSeats;
    private Set<Integer> vipSeats;
}
