package com.mstftrgt.hotelreservationsystem.reservation.exception;

public class LastMinuteCancellationException extends RuntimeException {
    public LastMinuteCancellationException() {
        super("Cannot cancel reservation : reservations can only be cancelled 2 day before check-in");
    }
}
