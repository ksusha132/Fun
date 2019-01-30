package com.epam.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class BookedTicketModel {
    LocalDateTime time;
    Integer seat;
    private EventModel eventModel;

    public EventModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookedTicketModel)) return false;
        BookedTicketModel that = (BookedTicketModel) o;
        return Objects.equals(eventModel, that.eventModel) &&
                Objects.equals(time, that.time) &&
                Objects.equals(seat, that.seat);
    }

    @Override
    public int hashCode() {

        return Objects.hash(eventModel, time, seat);
    }
}
