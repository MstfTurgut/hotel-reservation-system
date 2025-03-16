package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.CommandDispatcher;
import com.mstftrgt.hotelreservationsystem.IntegrationEventHandler;
import com.mstftrgt.hotelreservationsystem.command.payment.refund.RefundPaymentCommand;
import com.mstftrgt.hotelreservationsystem.event.ReservationCancelledIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCancelledIntegrationEventHandler implements IntegrationEventHandler<ReservationCancelledIntegrationEvent> {

    private final CommandDispatcher commandDispatcher;

    @Override
    public void handle(ReservationCancelledIntegrationEvent event) {

        RefundPaymentCommand refundPaymentCommand = RefundPaymentCommand
                .builder()
                .reservationId(event.reservationId())
                .build();

        commandDispatcher.dispatch(refundPaymentCommand);
    }
}
