package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;

import java.util.UUID;

public record PaymentInitiatedDomainEvent(
        UUID paymentId,
        CardDetails cardDetails) implements DomainEvent {
}