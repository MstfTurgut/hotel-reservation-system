package com.mstftrgt.hotelreservationsystem.reservation.event;


import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;

import java.time.LocalDate;

public record ReservationCreated(
        Long reservationId,
        CardDetails cardDetails) implements DomainEvent {
}