package com.mstftrgt.hotelreservationsystem.reservation.exception;

public class InvalidStayDateException extends RuntimeException {

    public InvalidStayDateException(String message) {
        super("Invalid stay date: " + message);
    }
}