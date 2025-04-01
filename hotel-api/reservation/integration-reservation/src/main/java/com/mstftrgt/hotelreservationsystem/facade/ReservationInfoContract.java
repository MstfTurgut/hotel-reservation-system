package com.mstftrgt.hotelreservationsystem.facade;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ReservationInfoContract(
        String customerFullName,

        String customerPhoneNumber,

        String customerEmailAddress,

        String reservationCode,
        String confirmationCode,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        String roomTypeTitle,
        int adultGuestCount,
        int childGuestCount
) {}
