package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.query.reservation.findavailabilitiesforroomtypes.FindReservationAvailabilitiesForSuitableRoomTypesQuery;

import java.time.LocalDate;

public record FindReservationAvailabilitiesForSuitableRoomTypesRequest(

        int adultGuestCount,
        int childGuestCount,
        LocalDate checkInDate,
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
