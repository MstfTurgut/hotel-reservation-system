package com.mstftrgt.hotelreservationsystem.event;


import com.mstftrgt.hotelreservationsystem.IntegrationEvent;
import lombok.Builder;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record PaymentFailedIntegrationEvent(
        UUID reservationId,
        BigDecimal paymentAmount

) implements IntegrationEvent {}

