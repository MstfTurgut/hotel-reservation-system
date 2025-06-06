package com.mstftrgt.hotelreservationsystem.event;


import com.mstftrgt.hotelreservationsystem.generic.integration.IntegrationEvent;
import lombok.Builder;
import java.util.UUID;
import java.math.BigDecimal;

@Builder
public record RefundFailedIntegrationEvent(
        UUID reservationId,
        BigDecimal paymentAmount
) implements IntegrationEvent {}
