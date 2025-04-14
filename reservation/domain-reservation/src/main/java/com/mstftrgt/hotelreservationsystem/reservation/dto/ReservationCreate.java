package com.mstftrgt.hotelreservationsystem.reservation.dto;

import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record ReservationCreate (
        UUID userId,
        UUID roomId,
        int adultGuestCount,
        int childGuestCount,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        String customerFullName,
        String customerPhoneNumber,
        String customerEmailAddress,
        String confirmationCode,
        String reservationCode,
        BigDecimal totalPrice,
        CardDetails cardDetails
) {
}
