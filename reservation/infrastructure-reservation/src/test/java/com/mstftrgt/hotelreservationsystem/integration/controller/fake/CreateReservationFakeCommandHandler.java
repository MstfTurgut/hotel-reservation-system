package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.command.reservation.create.CreateReservationCommand;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotAvailableException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_NOT_AVAILABLE_ROOM_TYPE_ID;


@Service
public class CreateReservationFakeCommandHandler implements CommandHandler<CreateReservationCommand, UUID> {

    @Override
    public UUID handle(CreateReservationCommand command) {
        if(RESERVATION_NOT_AVAILABLE_ROOM_TYPE_ID.equals(command.roomTypeId())) {
            throw new ReservationNotAvailableException();
        }

        return UUID.randomUUID();
    }
}
