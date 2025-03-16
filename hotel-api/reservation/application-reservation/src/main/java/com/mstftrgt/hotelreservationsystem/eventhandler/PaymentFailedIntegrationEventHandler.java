package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.CommandDispatcher;
import com.mstftrgt.hotelreservationsystem.IntegrationEventHandler;
import com.mstftrgt.hotelreservationsystem.command.reservation.rollback.RollbackReservationCommand;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentFailedIntegrationEventHandler implements IntegrationEventHandler<PaymentFailedIntegrationEvent> {

    private final CommandDispatcher commandDispatcher;

    @Override
    public void handle(PaymentFailedIntegrationEvent event) {

        RollbackReservationCommand rollbackReservationCommand = RollbackReservationCommand
                .builder()
                .reservationId(event.reservationId())
                .build();

        commandDispatcher.dispatch(rollbackReservationCommand);
    }
}
