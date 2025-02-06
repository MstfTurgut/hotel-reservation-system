package com.mstftrgt.hotelreservationsystem.payment.port;

import com.mstftrgt.hotelreservationsystem.reservation.model.CardDetails;
import com.mstftrgt.hotelreservationsystem.payment.model.Payment;

import java.math.BigDecimal;

public interface PaymentPort {

    Long processPayment(CardDetails cardDetails, BigDecimal totalPrice);

    Payment refundPayment(Long paymentId);

    Payment save(Payment payment);

}
