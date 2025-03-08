package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;

public record PaymentFailed(Long reservationId) implements DomainEvent {
}