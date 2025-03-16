package com.mstftrgt.hotelreservationsystem.facade;

import java.util.UUID;

public interface ReservationFacade {

    ReservationInfoContract findReservationById(UUID reservationId);
}
