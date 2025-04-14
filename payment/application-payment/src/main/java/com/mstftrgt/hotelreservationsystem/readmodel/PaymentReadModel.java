package com.mstftrgt.hotelreservationsystem.readmodel;

import com.mstftrgt.hotelreservationsystem.model.Payment;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Builder
public record PaymentReadModel(
        UUID id,
        UUID transactionId,
        UUID reservationId,
        BigDecimal amount,
        LocalDateTime createDate,
        String status
) {

    public static PaymentReadModel from(Payment payment) {
        return PaymentReadModel
                .builder()
                .id(payment.getId())
                .transactionId(payment.getTransactionId())
                .reservationId(payment.getReservationId())
                .amount(payment.getAmount())
                .createDate(payment.getCreateDate())
                .status(payment.getStatus().toString())
                .build();
    }
}
