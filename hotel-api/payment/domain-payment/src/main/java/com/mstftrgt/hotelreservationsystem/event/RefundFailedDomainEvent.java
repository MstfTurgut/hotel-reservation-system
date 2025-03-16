package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;
import java.util.UUID;
import java.math.BigDecimal;

public record RefundFailedDomainEvent(UUID reservationId, BigDecimal paymentAmount) implements DomainEvent {
}