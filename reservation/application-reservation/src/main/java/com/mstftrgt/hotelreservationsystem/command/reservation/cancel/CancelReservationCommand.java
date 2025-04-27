package com.mstftrgt.hotelreservationsystem.command.reservation.cancel;

import com.mstftrgt.hotelreservationsystem.generic.application.Command;
import lombok.Builder;
import java.util.UUID;

@Builder
public record CancelReservationCommand(UUID reservationId, String confirmationCode) implements Command {
}