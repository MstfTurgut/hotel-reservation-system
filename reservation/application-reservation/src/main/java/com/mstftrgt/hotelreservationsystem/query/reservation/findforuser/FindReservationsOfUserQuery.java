package com.mstftrgt.hotelreservationsystem.query.reservation.findforuser;

import com.mstftrgt.hotelreservationsystem.generic.application.Query;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationReadModel;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record FindReservationsOfUserQuery() implements Query<List<ReservationReadModel>> {
}
