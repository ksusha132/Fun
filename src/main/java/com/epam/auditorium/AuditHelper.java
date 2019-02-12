package com.epam.auditorium;

import java.util.List;

public class AuditHelper {
    public static void vipseatParser(String vipSeasString, List<Integer> vipSeats) {
        String[] seats = vipSeasString.split(",");
        for (String i : seats) {
            vipSeats.add(Integer.valueOf(i));
        }
    }
}
