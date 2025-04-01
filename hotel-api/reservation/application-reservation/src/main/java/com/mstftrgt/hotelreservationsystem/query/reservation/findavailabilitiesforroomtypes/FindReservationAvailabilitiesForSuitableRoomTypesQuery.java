package com.mstftrgt.hotelreservationsystem.query.reservation.findavailabilitiesforroomtypes;

import com.mstftrgt.hotelreservationsystem.Query;
import lombok.Builder;

import java.time.LocalDate;


@Builder
public record FindReservationAvailabilitiesForSuitableRoomTypesQuery(
        int adultGuestCount,
        int childGuestCount,
        LocalDate checkInDate,
        LocalDate checkOutDate) implements Query { }
