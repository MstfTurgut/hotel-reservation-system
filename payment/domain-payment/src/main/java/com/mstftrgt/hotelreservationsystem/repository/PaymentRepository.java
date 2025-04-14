package com.mstftrgt.hotelreservationsystem.repository;

import com.mstftrgt.hotelreservationsystem.model.Payment;

import java.util.UUID;
import java.util.Optional;

public interface PaymentRepository {


    void save(Payment payment);

    Optional<Payment> findById(UUID paymentId);

    Optional<Payment> findByReservationId(UUID reservationId);

}
