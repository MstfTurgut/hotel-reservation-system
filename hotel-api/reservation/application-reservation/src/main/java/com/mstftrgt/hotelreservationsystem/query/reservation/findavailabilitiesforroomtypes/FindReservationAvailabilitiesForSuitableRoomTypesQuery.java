package com.mstftrgt.hotelreservationsystem.query.reservation.findavailabilitiesforroomtypes;

import com.mstftrgt.hotelreservationsystem.Query;

import java.time.LocalDate;


public record FindReservationAvailabilitiesForSuitableRoomTypesQuery(
        int adultGuestCount,
        int childGuestCount,
        LocalDate checkInDate,
        LocalDate checkOutDate) implements Query { }
