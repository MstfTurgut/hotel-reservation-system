package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;
import lombok.Builder;
import java.util.UUID;

import java.math.BigDecimal;

@Builder
public record PaymentCompletedDomainEvent(
        UUID reservationId,
        BigDecimal paymentAmount
) implements DomainEvent {
}
