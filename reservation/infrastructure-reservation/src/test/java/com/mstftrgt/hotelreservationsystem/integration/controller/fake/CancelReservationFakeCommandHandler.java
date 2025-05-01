package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.command.reservation.cancel.CancelReservationCommand;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ConfirmationCodeIsNotValidException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.LastMinuteCancellationException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCancelledException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
import org.springframework.stereotype.Service;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.INVALID_CONFIRMATION_CODE;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_ALREADY_CANCELLED_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_LAST_MINUTE_CANCELLATION_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_NOT_FOUND_ID;

@Service
public class CancelReservationFakeCommandHandler implements VoidCommandHandler<CancelReservationCommand> {

    @Override
    public void handle(CancelReservationCommand command) {

        if (RESERVATION_NOT_FOUND_ID.equals(command.reservationId())) {
            throw new ReservationNotFoundException(command.reservationId());
        } else if (INVALID_CONFIRMATION_CODE.equals(command.confirmationCode())) {
            throw new ConfirmationCodeIsNotValidException();
        } else if (RESERVATION_ALREADY_CANCELLED_ID.equals(command.reservationId())) {
            throw new ReservationAlreadyCancelledException();
        } else if (RESERVATION_LAST_MINUTE_CANCELLATION_ID.equals(command.reservationId())) {
            throw new LastMinuteCancellationException();
        }
    }
}
