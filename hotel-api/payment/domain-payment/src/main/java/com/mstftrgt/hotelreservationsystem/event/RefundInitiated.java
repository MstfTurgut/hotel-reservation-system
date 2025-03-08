package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;

public record RefundInitiated(Long reservationId) implements DomainEvent {
}