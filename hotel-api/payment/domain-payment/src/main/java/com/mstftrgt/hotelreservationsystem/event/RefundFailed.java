package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;

public record RefundFailed(Long reservationId) implements DomainEvent {
}