package com.mstftrgt.hotelreservationsystem.command.reservation.checkin;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import java.util.UUID;

@Builder
public record CheckInReservationCommand(UUID reservationId, String confirmationCode) implements Command {
}