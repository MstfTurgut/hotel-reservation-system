package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.query.reservation.findavailabilitiesforroomtypes.FindReservationAvailabilitiesForSuitableRoomTypesQuery;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FindReservationAvailabilitiesForSuitableRoomTypesRequest(

        @Min(1)
        int adultGuestCount,

        @Min(0)
        int childGuestCount,

        @NotNull
        @FutureOrPresent
        LocalDate checkInDate,

        @NotNull
        @Future
        LocalDate checkOutDate

) {
    public FindReservationAvailabilitiesForSuitableRoomTypesQuery toQuery() {
        return FindReservationAvailabilitiesForSuitableRoomTypesQuery.builder()
                .adultGuestCount(adultGuestCount)
                .childGuestCount(childGuestCount)
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .build();
    }
}