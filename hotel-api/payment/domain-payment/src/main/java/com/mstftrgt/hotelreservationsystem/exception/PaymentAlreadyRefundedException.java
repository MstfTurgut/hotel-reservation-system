package com.mstftrgt.hotelreservationsystem.exception;

public class PaymentAlreadyRefundedException extends RuntimeException {
    public PaymentAlreadyRefundedException() {
        super("Payment is already refunded");
    }
}
