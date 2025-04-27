package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.command.payment.refund.RefundPaymentCommand;
import com.mstftrgt.hotelreservationsystem.event.ReservationCancelledIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationCancelledIntegrationEventHandler {

    private final CommandBus commandBus;

    @EventListener
    public void handle(ReservationCancelledIntegrationEvent event) {
        log.info("Handling integration event {}" , event);

        RefundPaymentCommand refundPaymentCommand = RefundPaymentCommand
                .builder()
                .reservationId(event.reservationId())
                .build();

        commandBus.dispatch(refundPaymentCommand);
    }
}
