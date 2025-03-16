package com.mstftrgt.hotelreservationsystem.reservation.event;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;
import java.util.UUID;

public record ReservationCancelledDomainEvent(UUID reservationId) implements DomainEvent {
}