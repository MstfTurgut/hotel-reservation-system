package com.mstftrgt.hotelreservationsystem.reservation.dto;

import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.model.CustomerDetails;
import com.mstftrgt.hotelreservationsystem.reservation.model.GuestSpecification;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record ReservationCreate (
        UUID userId,
        UUID roomId,
        GuestSpecification guestSpecification,
        StayDate stayDate,
        CustomerDetails customerDetails,
        String confirmationCode,
        String reservationCode,
        BigDecimal totalPrice,
        CardDetails cardDetails
) {
}
