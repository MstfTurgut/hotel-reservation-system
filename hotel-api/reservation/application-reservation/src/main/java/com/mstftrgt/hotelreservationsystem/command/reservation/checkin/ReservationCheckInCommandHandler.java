package com.mstftrgt.hotelreservationsystem.command.reservation.checkin;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
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
        Reservation reservation = reservationRepository.findById(command.reservationId())
                .orElseThrow(() -> new ReservationNotFoundException(command.reservationId()));

        reservation.checkIn();

        reservationRepository.save(reservation);
    }
}
