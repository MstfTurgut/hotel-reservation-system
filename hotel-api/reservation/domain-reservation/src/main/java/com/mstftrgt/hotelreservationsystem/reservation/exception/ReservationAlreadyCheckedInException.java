package com.mstftrgt.hotelreservationsystem.reservation.exception;

public class ReservationAlreadyCheckedInException extends RuntimeException {
    public ReservationAlreadyCheckedInException() {
        super("Invalid reservation status: reservation is already checked in");
    }
}
