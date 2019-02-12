package com.epam.auditorium;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class AuditRed {

    private String name;
    private Integer numberOfSeats;
    private List<Integer> vipSeatsPared = new ArrayList<>();

    @Autowired
    public AuditRed(@Value("${red.name}") String name,
                    @Value("${red.numberOfSeats}") String numberOfSeats,
                    @Value("${red.vipSeats}") String vipSeats) {
        this.name = name;
        this.numberOfSeats = Integer.parseInt(numberOfSeats);
        AuditHelper.vipseatParser(vipSeats, this.vipSeatsPared);
    }
}
