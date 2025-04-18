package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.command.reservation.create.CreateReservationCommand;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateReservationRequest(

        @NotNull
        UUID roomTypeId,

        @NotNull
        @FutureOrPresent
        LocalDate checkInDate,

        @NotNull
        @Future
        LocalDate checkOutDate,

        @Min(1)
        int adultGuestCount,

        @Min(0)
        int childGuestCount,

        @NotBlank
        String fullName,

        @NotBlank
        String phoneNumber,

        @NotBlank
        @Email
        String emailAddress,

        @NotNull
        @Positive
        BigDecimal totalPrice,

        @Valid
        @NotNull
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