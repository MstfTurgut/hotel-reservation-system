package com.mstftrgt.hotelreservationsystem.reservation.event;

import com.mstftrgt.hotelreservationsystem.generic.domain.DomainEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ReservationCancelledDomainEvent(UUID reservationId) implements DomainEvent {
}