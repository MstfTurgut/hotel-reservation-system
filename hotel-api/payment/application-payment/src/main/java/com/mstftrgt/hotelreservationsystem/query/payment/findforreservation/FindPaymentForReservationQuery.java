package com.mstftrgt.hotelreservationsystem.query.payment.findforreservation;

import com.mstftrgt.hotelreservationsystem.Query;
import lombok.Builder;

import java.util.UUID;

@Builder
public record FindPaymentForReservationQuery(
        UUID reservationId
) implements Query {
}
