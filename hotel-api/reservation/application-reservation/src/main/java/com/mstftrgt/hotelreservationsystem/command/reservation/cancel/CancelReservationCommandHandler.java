package com.mstftrgt.hotelreservationsystem.command.reservation.cancel;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.DomainEventPublisher;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCancelled;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelReservationCommandHandler implements CommandHandler<CancelReservationCommand> {

    private final ReservationRepository reservationRepository;
    private final DomainEventPublisher eventPublisher;

    @Override
    public void handle(CancelReservationCommand command) {
        Reservation reservation = reservationRepository.findById(command.getReservationId()).orElseThrow(
                () -> new IllegalArgumentException("Reservation not found"));

        reservation.confirmCustomer(command.getConfirmationCode());

        reservation.cancel();

        reservationRepository.update(reservation);

        eventPublisher.publish(new ReservationCancelled(command.getReservationId()));
    }
}
