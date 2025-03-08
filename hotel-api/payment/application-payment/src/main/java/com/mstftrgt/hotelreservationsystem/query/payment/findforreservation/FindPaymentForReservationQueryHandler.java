package com.mstftrgt.hotelreservationsystem.query.payment.findforreservation;

import com.mstftrgt.hotelreservationsystem.QueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.PaymentReadModel;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindPaymentForReservationQueryHandler implements QueryHandler<FindPaymentForReservationQuery, PaymentReadModel> {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentReadModel handle(FindPaymentForReservationQuery query) {
        return paymentRepository.findByReservationId(query.reservationId()).map(PaymentReadModel::from)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }
}
