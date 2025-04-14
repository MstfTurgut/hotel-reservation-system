package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.DomainEvent;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Builder;
import lombok.With;

import java.util.UUID;

@With
@Builder
public record PaymentInitiatedDomainEvent(
        UUID paymentId,
        CardDetails cardDetails) implements DomainEvent {
}