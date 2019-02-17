package com.epam.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

@Getter
@Setter
public class EventModel {
    private Integer id;
    private String name;
    private String rating;
    private double basePrice;
    private String auditoriumName;
    private String datesEvent;

    private NavigableSet<LocalDateTime> airDates = new TreeSet<>();
    private NavigableMap<LocalDateTime, AuditoriumModel> auditoriums = new TreeMap<>();


    /**
     * Checks if event is aired on particular <code>dateTime</code> and assigns
     * auditoriumModel to it.
     *
     * @param dateTime   Date and time of aired event for which to assign
     * @param auditoriumModel AuditoriumModel that should be assigned
     * @return <code>true</code> if successful, <code>false</code> if event is
     * not aired on that date
     */
    public boolean assignAuditorium(LocalDateTime dateTime, AuditoriumModel auditoriumModel) {
        if (airDates.contains(dateTime)) {
            auditoriums.put(dateTime, auditoriumModel);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes auditorium assignment from event
     *
     * @param dateTime Date and time to remove auditorium for
     * @return <code>true</code> if successful, <code>false</code> if not
     * removed
     */
    public boolean removeAuditoriumAssignment(LocalDateTime dateTime) {
        return auditoriums.remove(dateTime) != null;
    }

    /**
     * Add date and time of event air
     *
     * @param dateTime Date and time to add
     * @return <code>true</code> if successful, <code>false</code> if already
     * there
     */
    public boolean addAirDateTime(LocalDateTime dateTime) {
        return airDates.add(dateTime);
    }

    /**
     * Adding date and time of event air and assigning auditoriumModel to that
     *
     * @param dateTime   Date and time to add
     * @param auditoriumModel AuditoriumModel to add if success in date time add
     * @return <code>true</code> if successful, <code>false</code> if already
     * there
     */
    public boolean addAirDateTime(LocalDateTime dateTime, AuditoriumModel auditoriumModel) {
        boolean result = airDates.add(dateTime);
        if (result) {
            auditoriums.put(dateTime, auditoriumModel);
        }
        return result;
    }

    /**
     * Removes the date and time of event air. If auditorium was assigned to
     * that date and time - the assignment is also removed
     *
     * @param dateTime Date and time to remove
     * @return <code>true</code> if successful, <code>false</code> if not there
     */
    public boolean removeAirDateTime(LocalDateTime dateTime) {
        boolean result = airDates.remove(dateTime);
        if (result) {
            auditoriums.remove(dateTime);
        }
        return result;
    }

    /**
     * Checks if event airs on particular date and time
     *
     * @param dateTime Date and time to check
     * @return <code>true</code> event airs on that date and time
     */
    public boolean airsOnDateTime(LocalDateTime dateTime) {
        return airDates.stream().anyMatch(dt -> dt.equals(dateTime));
    }

    /**
     * Checks if event airs on particular date
     *
     * @param date Date to ckeck
     * @return <code>true</code> event airs on that date
     */
    public boolean airsOnDate(LocalDate date) {
        return airDates.stream().anyMatch(dt -> dt.toLocalDate().equals(date));
    }

    /**
     * Checking if event airs on dates between <code>from</code> and
     * <code>to</code> inclusive
     *
     * @param from Start date to check
     * @param to   End date to check
     * @return <code>true</code> event airs on dates
     */
    public boolean airsOnDates(LocalDate from, LocalDate to) {
        return airDates.stream()
                .anyMatch(dt -> dt.toLocalDate().compareTo(from) >= 0 && dt.toLocalDate().compareTo(to) <= 0);
    }
}
