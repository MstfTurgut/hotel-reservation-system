package com.mstftrgt.hotelreservationsystem.reservation.exception;

public class ReservationNotAvailableException extends RuntimeException {
    public ReservationNotAvailableException() {
        super("Reservation not available: no available rooms for the selected dates");
    }
}
