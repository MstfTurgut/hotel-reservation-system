package com.mstftrgt.hotelreservationsystem.command.reservation.rollback;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RollbackReservationCommandHandler implements CommandHandler<RollbackReservationCommand> {

    private final ReservationRepository reservationRepository;

    @Override
    public void handle(RollbackReservationCommand command) {
        reservationRepository.deleteById(command.reservationId());
    }
}
