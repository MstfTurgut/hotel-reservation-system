package com.mstftrgt.hotelreservationsystem.query.reservation.findforuser;

import com.mstftrgt.hotelreservationsystem.Query;

public record FindReservationsOfUserQuery(
        Long userId
) implements Query {
}
