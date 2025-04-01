package com.mstftrgt.hotelreservationsystem.query.reservation.findforcustomer;

import com.mstftrgt.hotelreservationsystem.Query;
import lombok.Builder;

@Builder
public record FindReservationsOfCustomerQuery(
        String fullName,
        String phoneNumber
) implements Query {
}
