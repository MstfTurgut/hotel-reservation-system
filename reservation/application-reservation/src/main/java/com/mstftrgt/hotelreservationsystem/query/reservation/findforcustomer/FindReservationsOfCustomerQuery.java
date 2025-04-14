package com.mstftrgt.hotelreservationsystem.query.reservation.findforcustomer;

import com.mstftrgt.hotelreservationsystem.cqrs.Query;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationReadModel;
import lombok.Builder;

import java.util.List;

@Builder
public record FindReservationsOfCustomerQuery(
        String fullName,
        String phoneNumber
) implements Query<List<ReservationReadModel>> {
}
