package com.mstftrgt.hotelreservationsystem.reservation.exception;

public class StayDateInPastException extends RuntimeException {

    public StayDateInPastException() {
        super("Invalid stay date: stay date cannot be in past");
    }
}