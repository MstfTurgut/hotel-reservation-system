package com.mstftrgt.hotelreservationsystem.query.reservation.findforuser;

import com.mstftrgt.hotelreservationsystem.Query;
import lombok.Builder;

import java.util.UUID;

@Builder
public record FindReservationsOfUserQuery(
        UUID userId
) implements Query {
}
