package com.mstftrgt.hotelreservationsystem.query.payment.findforreservation;

import com.mstftrgt.hotelreservationsystem.Query;

public record FindPaymentForReservationQuery(
        Long reservationId
) implements Query {
}
