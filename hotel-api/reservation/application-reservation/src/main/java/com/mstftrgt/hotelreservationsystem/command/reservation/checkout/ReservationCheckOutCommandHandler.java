package com.mstftrgt.hotelreservationsystem.command.reservation.checkout;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCheckOutCommandHandler implements CommandHandler<ReservationCheckOutCommand> {

    private final ReservationRepository reservationRepository;

    @Override
    public void handle(ReservationCheckOutCommand command) {
        Reservation reservation = reservationRepository.findById(command.getReservationId()).orElseThrow(
                () -> new IllegalArgumentException("Reservation not found"));

        reservation.checkOut();

        reservationRepository.update(reservation);
    }
}
