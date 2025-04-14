package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.command.reservation.create.CreateReservationCommand;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateReservationRequest(
        UUID roomTypeId,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        int adultGuestCount,
        int childGuestCount,
        String fullName,
        String phoneNumber,
        String emailAddress,
        BigDecimal totalPrice,
        CardDetails cardDetails
) {

    public CreateReservationCommand toCommand(UUID userId) {
        return CreateReservationCommand.builder()
                .userId(userId)
                .roomTypeId(roomTypeId)
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .adultGuestCount(adultGuestCount)
                .childGuestCount(childGuestCount)
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .emailAddress(emailAddress)
                .totalPrice(totalPrice)
                .cardDetails(cardDetails)
                .build();
    }
}
