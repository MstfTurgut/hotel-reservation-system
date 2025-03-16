package com.mstftrgt.hotelreservationsystem.reservation.exception;

import java.util.UUID;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(UUID reservationId) {
        super("Reservation not found with id : " + reservationId);
    }
}
