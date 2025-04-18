package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.command.reservation.cancel.CancelReservationCommand;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CancelReservationRequest(

        @NotBlank
        String confirmationCode

) {
    public CancelReservationCommand toCommand(UUID reservationId) {
        return CancelReservationCommand.builder()
                .confirmationCode(confirmationCode)
                .reservationId(reservationId)
                .build();
    }
}