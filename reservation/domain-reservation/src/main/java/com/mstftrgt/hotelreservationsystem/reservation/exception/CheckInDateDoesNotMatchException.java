package com.mstftrgt.hotelreservationsystem.reservation.exception;

public class CheckInDateDoesNotMatchException extends RuntimeException {
    public CheckInDateDoesNotMatchException() {
        super("Check-in date does not match the reservation's check-in date");
    }
}
