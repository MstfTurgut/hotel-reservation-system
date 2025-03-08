package com.mstftrgt.hotelreservationsystem.command.reservation.checkin;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCheckInCommandHandler implements CommandHandler<ReservationCheckInCommand> {

    private final ReservationRepository reservationRepository;

    @Override
    public void handle(ReservationCheckInCommand command) {
        Reservation reservation = reservationRepository.findById(command.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        reservation.checkIn();

        reservationRepository.update(reservation);
    }
}
