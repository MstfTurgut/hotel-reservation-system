package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.reservation.checkin.CheckInReservationCommand;
import com.mstftrgt.hotelreservationsystem.command.reservation.checkin.CheckInReservationCommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ConfirmationCodeIsNotValidException;
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
public class CheckInReservationTests {

    @Mock
    Reservation reservation;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private CheckInReservationCommandHandler handler;

    @Test
    void shouldCheckInReservationSuccessfully() {
        CheckInReservationCommand command = ApplicationTestDataFactory.getCheckInReservationTestCommand();

        when(reservationRepository.findById(command.reservationId())).thenReturn(Optional.of(reservation));

        handler.handle(command);

        verify(reservation).confirmCustomer(command.confirmationCode());
        verify(reservation).checkIn();
        verify(reservationRepository).save(reservation);
    }

    @Test
    void shouldNotSaveIfConfirmThrows() {
        CheckInReservationCommand command = ApplicationTestDataFactory.getCheckInReservationTestCommand();

        when(reservationRepository.findById(command.reservationId())).thenReturn(Optional.of(reservation));
        doThrow(ConfirmationCodeIsNotValidException.class).when(reservation).confirmCustomer(command.confirmationCode());

        assertThrows(ConfirmationCodeIsNotValidException.class, () -> handler.handle(command));

        verify(reservation).confirmCustomer(command.confirmationCode());
        verify(reservation, never()).checkIn();
        verify(reservationRepository, never()).save(reservation);
    }

    @Test
    void shouldThrowIfReservationNotFound() {
        CheckInReservationCommand command = ApplicationTestDataFactory.getCheckInReservationTestCommand();
        when(reservationRepository.findById(command.reservationId())).thenReturn(Optional.empty());

        assertThrows(ReservationNotFoundException.class, () -> handler.handle(command));

        verify(reservation, never()).confirmCustomer(any());
        verify(reservation, never()).checkIn();
        verify(reservationRepository, never()).save(any());
    }
}