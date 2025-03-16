package com.mstftrgt.hotelreservationsystem.query.reservation.findforuser;

import com.mstftrgt.hotelreservationsystem.Query;
import java.util.UUID;

public record FindReservationsOfUserQuery(
        UUID userId
) implements Query {
}
