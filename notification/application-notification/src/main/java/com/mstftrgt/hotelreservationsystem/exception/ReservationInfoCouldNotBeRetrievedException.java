package com.mstftrgt.hotelreservationsystem.exception;

import java.util.UUID;

public class ReservationInfoCouldNotBeRetrievedException extends RuntimeException {
    public ReservationInfoCouldNotBeRetrievedException(UUID reservationId) {
        super("Reservation info couldn't be retrieved for reservation id : " + reservationId);
    }
}
