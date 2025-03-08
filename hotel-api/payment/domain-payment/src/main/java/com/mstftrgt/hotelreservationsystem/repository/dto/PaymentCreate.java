package com.mstftrgt.hotelreservationsystem.repository.dto;

import com.mstftrgt.hotelreservationsystem.model.PaymentStatus;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentCreate(
        Long transactionId,
        Long reservationId,
        BigDecimal amount,
        PaymentStatus status
) {
}
