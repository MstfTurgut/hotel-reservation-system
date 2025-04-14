package com.mstftrgt.hotelreservationsystem.query.payment.findforreservation;

import com.mstftrgt.hotelreservationsystem.cqrs.Query;
import com.mstftrgt.hotelreservationsystem.readmodel.PaymentReadModel;
import lombok.Builder;

import java.util.UUID;

@Builder
public record FindPaymentForReservationQuery(
        UUID reservationId
) implements Query<PaymentReadModel> {
}
