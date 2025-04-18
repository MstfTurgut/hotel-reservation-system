package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.query.reservation.findforcustomer.FindReservationsOfCustomerQuery;
import jakarta.validation.constraints.NotBlank;

public record FindReservationsOfCustomerRequest(

        @NotBlank
        String fullName,

        @NotBlank
        String phoneNumber

) {
    public FindReservationsOfCustomerQuery toQuery() {
        return FindReservationsOfCustomerQuery.builder()
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .build();
    }
}