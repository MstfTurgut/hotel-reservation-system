package com.mstftrgt.hotelreservationsystem.query.reservation.findbyreservationcode;

import com.mstftrgt.hotelreservationsystem.generic.application.Query;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationReadModel;
import lombok.Builder;

@Builder
public record FindReservationByReservationCodeQuery(
        String reservationCode
) implements Query<ReservationReadModel> {

}
