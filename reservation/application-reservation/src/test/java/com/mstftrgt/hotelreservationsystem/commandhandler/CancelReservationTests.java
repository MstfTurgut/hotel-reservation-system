package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.reservation.cancel.CancelReservationCommand;
import com.mstftrgt.hotelreservationsystem.command.reservation.cancel.CancelReservationCommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ConfirmationCodeIsNotValidException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCancelledException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CancelReservationTests {

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    Reservation reservation;

    @InjectMocks
    CancelReservationCommandHandler handler;

    @Test
    public void shouldCancelReservationSuccessfully() {
        CancelReservationCommand command = ApplicationTestDataFactory.getCancelReservationTestCommand();

        when(reservationRepository.findById(command.reservationId()))
                .thenReturn(Optional.of(reservation));

        handler.handle(command);

        verify(reservation).confirmCustomer(command.confirmationCode());
        verify(reservation).cancel();
        verify(reservationRepository).save(reservation);
    }

    @Test
    public void shouldThrowIfReservationNotFound() {
        CancelReservationCommand command = ApplicationTestDataFactory.getCancelReservationTestCommand();

        when(reservationRepository.findById(command.reservationId()))
                .thenReturn(Optional.empty());

        assertThrows(ReservationNotFoundException.class,
                () -> handler.handle(command));

        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    public void shouldNotSaveIfConfirmThrows() {
        CancelReservationCommand command = ApplicationTestDataFactory.getCancelReservationTestCommand();

        when(reservationRepository.findById(command.reservationId())).thenReturn(Optional.of(reservation));
        doThrow(ConfirmationCodeIsNotValidException.class).when(reservation).confirmCustomer(command.confirmationCode());

        assertThrows(ConfirmationCodeIsNotValidException.class,
                () -> handler.handle(command));

        verify(reservation).confirmCustomer(command.confirmationCode());
        verify(reservation, never()).cancel();
        verify(reservationRepository, never()).save(reservation);
    }

    @Test
    public void shouldNotSaveIfCancelThrows() {
        CancelReservationCommand command = ApplicationTestDataFactory.getCancelReservationTestCommand();

        when(reservationRepository.findById(command.reservationId())).thenReturn(Optional.of(reservation));
        doThrow(ReservationAlreadyCancelledException.class).when(reservation).cancel();

        assertThrows(ReservationAlreadyCancelledException.class,
                () -> handler.handle(command));

        verify(reservation).confirmCustomer(command.confirmationCode());
        verify(reservation).cancel();
        verify(reservationRepository, never()).save(reservation);
    }
}
