package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.reservation.rollback.RollbackReservationCommand;
import com.mstftrgt.hotelreservationsystem.command.reservation.rollback.RollbackReservationCommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RollbackReservationTests {

    @Mock
    Reservation reservation;

    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    RollbackReservationCommandHandler handler;

    @Test
    void shouldRollbackReservationSuccessfully() {
        RollbackReservationCommand command = ApplicationTestDataFactory.getRollbackReservationTestCommand();

        when(reservationRepository.findById(command.reservationId())).thenReturn(Optional.of(reservation));

        handler.handle(command);

        verify(reservationRepository).delete(any(Reservation.class));
    }

}
