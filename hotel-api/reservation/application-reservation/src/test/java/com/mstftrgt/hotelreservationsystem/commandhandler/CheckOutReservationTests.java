package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.reservation.checkout.CheckOutReservationCommand;
import com.mstftrgt.hotelreservationsystem.command.reservation.checkout.CheckOutReservationCommandHandler;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckOutReservationTests {

    @Mock
    Reservation reservation;

    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    CheckOutReservationCommandHandler handler;

    @Test
    void shouldCheckOutReservationSuccessfully() {
        CheckOutReservationCommand command = ApplicationTestDataFactory.getCheckOutReservationTestCommand();

        when(reservationRepository.findById(command.reservationId())).thenReturn(Optional.of(reservation));

        handler.handle(command);

        verify(reservation).checkOut();
        verify(reservationRepository).save(reservation);
    }

    @Test
    void shouldThrowIfReservationNotFound() {
        CheckOutReservationCommand command = ApplicationTestDataFactory.getCheckOutReservationTestCommand();
        when(reservationRepository.findById(command.reservationId())).thenReturn(Optional.empty());

        assertThrows(ReservationNotFoundException.class, () -> handler.handle(command));

        verify(reservation, never()).checkOut();
        verify(reservationRepository, never()).save(any());
    }

}
