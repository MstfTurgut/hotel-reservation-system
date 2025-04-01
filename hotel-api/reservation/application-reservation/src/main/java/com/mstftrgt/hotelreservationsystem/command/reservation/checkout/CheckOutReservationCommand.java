package com.mstftrgt.hotelreservationsystem.command.reservation.checkout;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import java.util.UUID;

@Builder
public record CheckOutReservationCommand(UUID reservationId) implements Command {
}