package com.mstftrgt.hotelreservationsystem.query.reservation.findforcustomer;

import com.mstftrgt.hotelreservationsystem.Query;

public record FindReservationsOfCustomerQuery(
        String fullName,
        String phoneNumber
) implements Query {
}
