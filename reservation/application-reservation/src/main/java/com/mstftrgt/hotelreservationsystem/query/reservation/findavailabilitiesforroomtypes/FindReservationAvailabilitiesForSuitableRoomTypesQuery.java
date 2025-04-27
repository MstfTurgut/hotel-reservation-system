package com.mstftrgt.hotelreservationsystem.query.reservation.findavailabilitiesforroomtypes;

import com.mstftrgt.hotelreservationsystem.generic.application.Query;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationAvailabilityForRoomTypeReadModel;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;


@Builder
public record FindReservationAvailabilitiesForSuitableRoomTypesQuery(
        int adultGuestCount,
        int childGuestCount,
        LocalDate checkInDate,
        LocalDate checkOutDate) implements Query<List<ReservationAvailabilityForRoomTypeReadModel>> { }
