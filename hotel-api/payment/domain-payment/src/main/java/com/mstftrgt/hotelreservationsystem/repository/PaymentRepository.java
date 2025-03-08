package com.mstftrgt.hotelreservationsystem.repository;

import com.mstftrgt.hotelreservationsystem.repository.dto.PaymentCreate;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.model.Payment;

import java.math.BigDecimal;
import java.util.Optional;

public interface PaymentRepository {

    void save(PaymentCreate paymentCreate);

    long pay(BigDecimal amount, CardDetails cardDetails);

    Optional<Payment> findByReservationId(long reservationId);

    void initializeRefund(long transactionId);
}
