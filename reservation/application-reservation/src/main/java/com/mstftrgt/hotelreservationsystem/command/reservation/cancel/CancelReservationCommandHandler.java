package com.mstftrgt.hotelreservationsystem.command.reservation.cancel;

import com.mstftrgt.hotelreservationsystem.generic.application.CommandHandler;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelReservationCommandHandler implements VoidCommandHandler<CancelReservationCommand> {

    private final ReservationRepository reservationRepository;

    @Override
    public void handle(CancelReservationCommand command) {
        Reservation reservation = reservationRepository.findById(command.reservationId())
                .orElseThrow(() -> new ReservationNotFoundException(command.reservationId()));

        reservation.confirmCustomer(command.confirmationCode());

        reservation.cancel();

        reservationRepository.save(reservation);
    }
}
