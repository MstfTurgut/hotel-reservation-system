package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.command.reservation.rollback.RollbackReservationCommand;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentFailedIntegrationEventHandler {

    private final CommandBus commandBus;

    @EventListener
    public void handle(PaymentFailedIntegrationEvent event) {
        log.info("Handling integration event {}" , event);

        RollbackReservationCommand rollbackReservationCommand = RollbackReservationCommand
                .builder()
                .reservationId(event.reservationId())
                .build();

        commandBus.dispatch(rollbackReservationCommand);
    }
}
