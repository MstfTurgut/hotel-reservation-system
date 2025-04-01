package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.reservation.rollback.RollbackReservationCommand;
import com.mstftrgt.hotelreservationsystem.command.reservation.rollback.RollbackReservationCommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RollbackReservationTests {

    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    RollbackReservationCommandHandler handler;

    @Test
    void shouldRollbackReservationSuccessfully() {
        RollbackReservationCommand command = ApplicationTestDataFactory.getRollbackReservationTestCommand();

        handler.handle(command);

        verify(reservationRepository).deleteById(command.reservationId());
    }

}
