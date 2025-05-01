package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.command.reservation.checkout.CheckOutReservationCommand;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCheckedOutException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
import org.springframework.stereotype.Service;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_ALREADY_CHECKED_OUT_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_NOT_FOUND_ID;

@Service
public class CheckOutReservationFakeCommandHandler implements VoidCommandHandler<CheckOutReservationCommand> {

    @Override
    public void handle(CheckOutReservationCommand command) {

        if (RESERVATION_NOT_FOUND_ID.equals(command.reservationId())) {
            throw new ReservationNotFoundException(command.reservationId());
        } else if (RESERVATION_ALREADY_CHECKED_OUT_ID.equals(command.reservationId())) {
            throw new ReservationAlreadyCheckedOutException();
        }
    }
}
