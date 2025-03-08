package com.mstftrgt.hotelreservationsystem.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Payment {

    private Long id;
    private Long transactionId;
    private Long reservationId;
    private BigDecimal amount;
    private LocalDateTime createDate;
    private PaymentStatus status;

    public void refund() {
        if(status == PaymentStatus.REFUND_INITIATED) {
            throw new IllegalArgumentException("Payment is already refunded");
        }
        status = PaymentStatus.REFUND_INITIATED;
    }

    public void setPaid() {
        status = PaymentStatus.PAID;
    }
}
