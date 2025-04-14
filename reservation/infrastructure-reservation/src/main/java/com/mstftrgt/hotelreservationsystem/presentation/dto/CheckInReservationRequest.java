package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.command.reservation.checkin.CheckInReservationCommand;

import java.util.UUID;

public record CheckInReservationRequest(
        String confirmationCode

) {

    public CheckInReservationCommand toCommand(UUID reservationId) {
        return CheckInReservationCommand.builder()
                .confirmationCode(confirmationCode)
                .reservationId(reservationId)
                .build();
    }
}
