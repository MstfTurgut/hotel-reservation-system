package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.command.reservation.cancel.CancelReservationCommand;

import java.util.UUID;

public record CancelReservationRequest(
        String confirmationCode
) {

    public CancelReservationCommand toCommand(UUID reservationId) {
        return CancelReservationCommand.builder()
                .confirmationCode(confirmationCode)
                .reservationId(reservationId)
                .build();
    }

}
