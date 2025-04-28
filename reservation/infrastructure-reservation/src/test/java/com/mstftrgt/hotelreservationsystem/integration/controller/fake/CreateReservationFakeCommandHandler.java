package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.command.reservation.create.CreateReservationCommand;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotAvailableException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Primary
@Service
public class CreateReservationFakeCommandHandler implements CommandHandler<CreateReservationCommand, UUID> {

    private final static UUID RESERVATION_NOT_AVAILABLE_ROOM_TYPE_ID = UUID.fromString("99999999-9999-9999-9999-999999999999");

    @Override
    public UUID handle(CreateReservationCommand command) {
        if(RESERVATION_NOT_AVAILABLE_ROOM_TYPE_ID.equals(command.roomTypeId())) {
            throw new ReservationNotAvailableException();
        }
        return UUID.randomUUID();
    }
}
