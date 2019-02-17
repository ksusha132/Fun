package com.epam.auditorium;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class AuditGreen {

    private String name;
    private Integer numberOfSeats;
    private List<Integer> vipSeatsPared = new ArrayList<>();

    @Autowired
    public AuditGreen(@Value("${green.name}") String name,
                      @Value("${green.numberOfSeats}") String numberOfSeats,
                      @Value("${green.vipSeats}") String vipSeats) {
        this.name = name;
        this.numberOfSeats = Integer.parseInt(numberOfSeats);
        AuditHelper.vipSeatParser(vipSeats, this.vipSeatsPared);
    }
}
