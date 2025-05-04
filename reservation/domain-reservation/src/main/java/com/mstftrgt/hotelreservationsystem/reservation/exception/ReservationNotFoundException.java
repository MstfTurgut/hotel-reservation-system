package com.mstftrgt.hotelreservationsystem.reservation.exception;

import java.util.UUID;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(UUID reservationId) {
        super("Reservation not found with id : " + reservationId);
    }

    public ReservationNotFoundException(String reservationCode) {
        super("Reservation not found with reservation code : " + reservationCode);
    }
}
