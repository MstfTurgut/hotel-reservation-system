package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.command.reservation.checkin.CheckInReservationCommand;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.exception.CheckInDateDoesNotMatchException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ConfirmationCodeIsNotValidException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCheckedInException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
import org.springframework.stereotype.Service;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.INVALID_CONFIRMATION_CODE;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_ALREADY_CHECKED_IN_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_CHECK_IN_DATE_DOES_NOT_MATCH_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_NOT_FOUND_ID;

@Service
public class CheckInReservationFakeCommandHandler implements VoidCommandHandler<CheckInReservationCommand> {

    @Override
    public void handle(CheckInReservationCommand command) {

        if (RESERVATION_NOT_FOUND_ID.equals(command.reservationId())) {
            throw new ReservationNotFoundException(command.reservationId());
        } else if (INVALID_CONFIRMATION_CODE.equals(command.confirmationCode())) {
            throw new ConfirmationCodeIsNotValidException();
        } else if (RESERVATION_ALREADY_CHECKED_IN_ID.equals(command.reservationId())) {
            throw new ReservationAlreadyCheckedInException();
        } else if (RESERVATION_CHECK_IN_DATE_DOES_NOT_MATCH_ID.equals(command.reservationId())) {
            throw new CheckInDateDoesNotMatchException();
        }
    }
}
