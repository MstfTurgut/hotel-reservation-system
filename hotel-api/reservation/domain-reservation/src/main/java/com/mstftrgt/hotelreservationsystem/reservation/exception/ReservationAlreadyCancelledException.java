package com.mstftrgt.hotelreservationsystem.reservation.exception;

public class ReservationAlreadyCancelledException extends RuntimeException {
    public ReservationAlreadyCancelledException() {
        super("Invalid reservation status: reservation is already cancelled");
    }
}
