package com.mstftrgt.hotelreservationsystem.command.reservation.checkin;

import com.mstftrgt.hotelreservationsystem.cqrs.Command;
import lombok.Builder;
import java.util.UUID;

@Builder
public record CheckInReservationCommand(UUID reservationId, String confirmationCode) implements Command {
}