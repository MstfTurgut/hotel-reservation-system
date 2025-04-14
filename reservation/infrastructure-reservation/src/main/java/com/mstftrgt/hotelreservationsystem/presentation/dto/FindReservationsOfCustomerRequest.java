package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.query.reservation.findforcustomer.FindReservationsOfCustomerQuery;

public record FindReservationsOfCustomerRequest(

        String fullName,
        String phoneNumber
) {

    public FindReservationsOfCustomerQuery toQuery() {
        return FindReservationsOfCustomerQuery.builder()
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .build();
    }
}
