package com.mstftrgt.hotelreservationsystem.reservation.exception;

public class InverseStayDateException extends RuntimeException {

    public InverseStayDateException() {
        super("Invalid stay date: check-in must be before check-out");
    }
}