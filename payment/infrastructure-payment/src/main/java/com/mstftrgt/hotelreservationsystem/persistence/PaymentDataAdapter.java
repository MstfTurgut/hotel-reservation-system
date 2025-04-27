package com.mstftrgt.hotelreservationsystem.persistence;

import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.persistence.entity.PaymentEntity;
import com.mstftrgt.hotelreservationsystem.persistence.repository.PaymentJpaRepository;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentDataAdapter implements PaymentRepository {

    private final ApplicationEventPublisher publisher;
    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public void save(Payment payment) {
        paymentJpaRepository.save(PaymentEntity.from(payment));

        payment.publishAllEventsAndClear(publisher);
    }

    @Override
    public Optional<Payment> findById(UUID paymentId) {
        return paymentJpaRepository.findById(paymentId)
                .map(PaymentEntity::toModel);
    }

    @Override
    public Optional<Payment> findByReservationId(UUID reservationId) {
        return paymentJpaRepository.findByReservationId(reservationId)
                .map(PaymentEntity::toModel);
    }
}
