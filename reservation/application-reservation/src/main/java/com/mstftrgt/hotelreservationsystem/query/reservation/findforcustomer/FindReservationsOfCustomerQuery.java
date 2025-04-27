package com.mstftrgt.hotelreservationsystem.query.reservation.findforcustomer;

import com.mstftrgt.hotelreservationsystem.generic.application.Query;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationReadModel;
import lombok.Builder;

import java.util.List;

@Builder
public record FindReservationsOfCustomerQuery(
        String fullName,
        String phoneNumber
) implements Query<List<ReservationReadModel>> {
}
