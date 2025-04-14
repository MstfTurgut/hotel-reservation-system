package com.mstftrgt.hotelreservationsystem.exception;

import java.util.UUID;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(UUID paymentId) {
        super("Payment not found with id : " + paymentId);
    }

    public PaymentNotFoundException() {
        super("Payment not found");
    }
}
