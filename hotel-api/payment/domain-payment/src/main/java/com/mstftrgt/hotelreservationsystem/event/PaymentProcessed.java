package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;

import java.math.BigDecimal;

public record PaymentProcessed(BigDecimal amount, Long reservationId, Long transactionId) implements DomainEvent {
}