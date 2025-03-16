package com.mstftrgt.hotelreservationsystem.command.reservation.cancel;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.DomainEventPublisher;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCancelledDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CancelReservationCommandHandler implements CommandHandler<CancelReservationCommand> {

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
