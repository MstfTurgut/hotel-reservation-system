package com.mstftrgt.hotelreservationsystem.reservation.event;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;

public record ReservationCancelled(Long reservationId) implements DomainEvent {
}