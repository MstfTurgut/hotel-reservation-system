package com.mstftrgt.hotelreservationsystem.query.payment.findforreservation;

import com.mstftrgt.hotelreservationsystem.Query;
import java.util.UUID;

public record FindPaymentForReservationQuery(
        UUID reservationId
) implements Query {
}
