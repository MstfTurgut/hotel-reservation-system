package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.DomainEvent;
import lombok.Builder;
import java.util.UUID;
import java.math.BigDecimal;

@Builder
public record PaymentFailedDomainEvent(UUID reservationId, BigDecimal paymentAmount) implements DomainEvent {
}