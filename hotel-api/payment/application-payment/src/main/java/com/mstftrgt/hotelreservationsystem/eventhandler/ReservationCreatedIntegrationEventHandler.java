package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.CommandDispatcher;
import com.mstftrgt.hotelreservationsystem.IntegrationEventHandler;
import com.mstftrgt.hotelreservationsystem.command.payment.initiate.InitiatePaymentCommand;
import com.mstftrgt.hotelreservationsystem.event.ReservationCreatedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCreatedIntegrationEventHandler implements IntegrationEventHandler<ReservationCreatedIntegrationEvent> {

    private final CommandDispatcher commandDispatcher;

    @Override
    public void handle(ReservationCreatedIntegrationEvent event) {
            commandDispatcher.dispatch(
                    InitiatePaymentCommand.builder()
                            .reservationId(event.reservationId())
                            .paymentAmount(event.totalPrice())
                            .cardDetails(event.cardDetails())
                            .build());
    }
}
