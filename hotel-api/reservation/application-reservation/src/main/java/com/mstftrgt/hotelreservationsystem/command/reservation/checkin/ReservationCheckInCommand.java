package com.mstftrgt.hotelreservationsystem.command.reservation.checkin;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationCheckInCommand implements Command {
    private final Long reservationId;
    private final String confirmationCode;
}