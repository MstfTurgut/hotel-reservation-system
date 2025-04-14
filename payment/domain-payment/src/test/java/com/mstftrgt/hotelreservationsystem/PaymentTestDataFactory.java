package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.dto.PaymentCreate;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.model.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentTestDataFactory {

    public static PaymentCreate getTestPaymentCreate() {
        return PaymentCreate.builder()
                .reservationId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .amount(BigDecimal.TEN)
                .build();
    }


    public static Payment getTestPayment() {
        return Payment.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .reservationId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .amount(BigDecimal.TEN)
                .createDate(LocalDateTime.of(2030, 1, 1, 0, 0))
                .build();
    }

    public static CardDetails getTestCardDetails() {
        return CardDetails.builder()
                .cardNumber("0000000000000000")
                .cardHolderName("testHolderName")
                .expiryDate("07/26")
                .cvv("912")
                .build();
    }

    public static Payment getTestPaymentWith(PaymentStatus paymentStatus) {
        return getTestPayment().withStatus(paymentStatus);
    }
}
