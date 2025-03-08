package com.mstftrgt.hotelreservationsystem.command.reservation.cancel;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CancelReservationCommand implements Command {
    private final Long reservationId;
    private final String confirmationCode;
}