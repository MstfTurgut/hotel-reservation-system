package com.mstftrgt.hotelreservationsystem.readmodel;

import com.mstftrgt.hotelreservationsystem.model.Payment;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentReadModel(
        Long id,
        Long transactionId,
        Long reservationId,
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
