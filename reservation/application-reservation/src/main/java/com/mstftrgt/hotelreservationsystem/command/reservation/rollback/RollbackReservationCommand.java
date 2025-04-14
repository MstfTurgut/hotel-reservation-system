package com.mstftrgt.hotelreservationsystem.command.reservation.rollback;

import com.mstftrgt.hotelreservationsystem.cqrs.Command;
import lombok.Builder;
import java.util.UUID;

@Builder
public record RollbackReservationCommand(UUID reservationId) implements Command {
}
