package com.mstftrgt.hotelreservationsystem.command.reservation.rollback;

import com.mstftrgt.hotelreservationsystem.generic.application.Command;
import lombok.Builder;
import java.util.UUID;

@Builder
public record RollbackReservationCommand(UUID reservationId) implements Command {
}
