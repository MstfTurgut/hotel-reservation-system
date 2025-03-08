package com.mstftrgt.hotelreservationsystem.command.reservation.create;


import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
public abstract class CreateReservationCommand implements Command {
    private final Long userId;
    private final Long roomTypeId;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final int adultGuestCount;
    private final int childGuestCount;
    private final String fullName;
    private final String phoneNumber;
    private final String emailAddress;
    private final CardDetails cardDetails;
}