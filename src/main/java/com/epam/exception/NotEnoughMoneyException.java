package com.epam.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(Double balance) {
        super("balance = " + balance + " is not enough to pay for ticket.");
    }
}
