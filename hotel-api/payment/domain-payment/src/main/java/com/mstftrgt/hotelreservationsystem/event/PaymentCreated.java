package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;

import java.math.BigDecimal;

public record PaymentCreated(
        Long reservationId,
        BigDecimal amount) implements DomainEvent {
}