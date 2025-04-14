package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.IntegrationEventHandler;
import com.mstftrgt.hotelreservationsystem.command.payment.refund.RefundPaymentCommand;
import com.mstftrgt.hotelreservationsystem.cqrs.CommandBus;
import com.mstftrgt.hotelreservationsystem.event.ReservationCancelledIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCancelledIntegrationEventHandler extends IntegrationEventHandler<ReservationCancelledIntegrationEvent> {

    private final CommandBus commandBus;

    @Override
    public void handle(ReservationCancelledIntegrationEvent event) {
        RefundPaymentCommand refundPaymentCommand = RefundPaymentCommand
                .builder()
                .reservationId(event.reservationId())
                .build();

        commandBus.dispatch(refundPaymentCommand);
    }
}
