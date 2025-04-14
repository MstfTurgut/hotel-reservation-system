package com.mstftrgt.hotelreservationsystem.command.reservation.create;


import com.mstftrgt.hotelreservationsystem.cqrs.Command;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CreateReservationCommand(
        UUID userId,
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