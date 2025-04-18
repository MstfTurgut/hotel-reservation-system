package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.command.reservation.checkin.CheckInReservationCommand;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CheckInReservationRequest(

        @NotBlank
        String confirmationCode

) {
    public CheckInReservationCommand toCommand(UUID reservationId) {
        return CheckInReservationCommand.builder()
                .confirmationCode(confirmationCode)
                .reservationId(reservationId)
                .build();
    }
}