package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.IntegrationEventHandler;
import com.mstftrgt.hotelreservationsystem.command.reservation.rollback.RollbackReservationCommand;
import com.mstftrgt.hotelreservationsystem.cqrs.CommandBus;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentFailedIntegrationEventHandler extends IntegrationEventHandler<PaymentFailedIntegrationEvent> {

    private final CommandBus commandBus;

    @Override
    public void handle(PaymentFailedIntegrationEvent event) {
        RollbackReservationCommand rollbackReservationCommand = RollbackReservationCommand
                .builder()
                .reservationId(event.reservationId())
                .build();

        commandBus.dispatch(rollbackReservationCommand);
    }
}
