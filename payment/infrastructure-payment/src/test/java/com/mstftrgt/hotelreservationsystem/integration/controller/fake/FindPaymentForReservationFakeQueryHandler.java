package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.exception.PaymentNotFoundException;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryHandler;
import com.mstftrgt.hotelreservationsystem.query.payment.findforreservation.FindPaymentForReservationQuery;
import com.mstftrgt.hotelreservationsystem.readmodel.PaymentReadModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.PAYMENT_NOT_FOUND_RESERVATION_ID;


@Service
public class FindPaymentForReservationFakeQueryHandler implements QueryHandler<FindPaymentForReservationQuery, PaymentReadModel> {

    @Override
    public PaymentReadModel handle(FindPaymentForReservationQuery query) {
        if (PAYMENT_NOT_FOUND_RESERVATION_ID.equals(query.reservationId())) {
            throw new PaymentNotFoundException();
        }

        return PaymentReadModel.builder()
                .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .transactionId(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .reservationId(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .amount(BigDecimal.TEN)
                .createDate(LocalDateTime.now())
                .status("COMPLETED")
                .build();
    }
}
