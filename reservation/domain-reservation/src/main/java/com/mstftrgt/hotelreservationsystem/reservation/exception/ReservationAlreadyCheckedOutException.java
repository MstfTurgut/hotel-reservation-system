package com.mstftrgt.hotelreservationsystem.reservation.exception;

public class ReservationAlreadyCheckedOutException extends RuntimeException {
    public ReservationAlreadyCheckedOutException() {
        super("Invalid reservation status: reservation is already checked out");
    }
}
