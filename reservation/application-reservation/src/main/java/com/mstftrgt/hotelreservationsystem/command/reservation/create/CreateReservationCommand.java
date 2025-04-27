package com.mstftrgt.hotelreservationsystem.command.reservation.create;


import com.mstftrgt.hotelreservationsystem.generic.application.Command;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CreateReservationCommand(
        UUID roomTypeId,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        int adultGuestCount,
        int childGuestCount,
        String fullName,
        String phoneNumber,
        String emailAddress,
        BigDecimal totalPrice,
        CardDetails cardDetails) implements Command {
}