package com.epam.auditorium;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class AuditBlue {
    private String name;
    private Integer numberOfSeats;
    private List<Integer> vipSeatsPared = new ArrayList<>();

    @Autowired
    public AuditBlue(@Value("${blue.name}") String name,
                     @Value("${blue.numberOfSeats}") String numberOfSeats,
                     @Value("${blue.vipSeats}") String vipSeats) {
        this.name = name;
        this.numberOfSeats = Integer.parseInt(numberOfSeats);
        AuditHelper.vipseatParser(vipSeats, this.vipSeatsPared);
    }
}
