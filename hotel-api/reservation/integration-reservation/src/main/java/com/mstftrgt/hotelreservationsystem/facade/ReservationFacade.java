package com.mstftrgt.hotelreservationsystem.facade;

import java.util.Optional;
import java.util.UUID;

public interface ReservationFacade {

    Optional<ReservationInfoContract> findReservationById(UUID reservationId);
}
