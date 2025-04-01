package com.mstftrgt.hotelreservationsystem.command.reservation.create;


import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.reservation.dto.ReservationCreate;
import com.mstftrgt.hotelreservationsystem.reservation.model.CustomerDetails;
import com.mstftrgt.hotelreservationsystem.reservation.model.GuestSpecification;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import lombok.Builder;
import lombok.Data;

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