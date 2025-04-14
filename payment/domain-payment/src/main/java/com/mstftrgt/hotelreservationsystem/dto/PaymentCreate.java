package com.mstftrgt.hotelreservationsystem.dto;

import java.util.UUID;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentCreate(
        UUID reservationId,
        BigDecimal amount
) {
}
