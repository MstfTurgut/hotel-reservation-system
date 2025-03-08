package com.mstftrgt.hotelreservationsystem.command.reservation.rollback;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RollbackReservationCommand implements Command {
    private final long reservationId;
}
