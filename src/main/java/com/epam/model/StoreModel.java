package com.epam.model;

import java.util.HashSet;
import java.util.Set;

public class StoreModel {
    static Set<BookedTicketModel> bookedTicketModels = new HashSet();

    public static Set<BookedTicketModel> getBookedTicketModels() {
        return bookedTicketModels;
    } // todo create table and put it into db

    public static void setToBoockedTicketsList(BookedTicketModel bookedTicketModel) {
        if (bookedTicketModels.contains(bookedTicketModel)) {
            throw new RuntimeException("There is an error while book ticket");
        }
        if (bookedTicketModel == null) {
            throw new RuntimeException("the ticket is empty");
        }
        bookedTicketModels.add(bookedTicketModel);
    }
}
