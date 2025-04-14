package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.IntegrationEventHandler;
import com.mstftrgt.hotelreservationsystem.command.payment.initiate.InitiatePaymentCommand;
import com.mstftrgt.hotelreservationsystem.cqrs.CommandBus;
import com.mstftrgt.hotelreservationsystem.event.ReservationCreatedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCreatedIntegrationEventHandler extends IntegrationEventHandler<ReservationCreatedIntegrationEvent> {

    private final CommandBus commandBus;

    @Override
    public void handle(ReservationCreatedIntegrationEvent event) {
        InitiatePaymentCommand initiatePaymentCommand = InitiatePaymentCommand.builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.totalPrice())
                .cardDetails(event.cardDetails())
                .build();

        commandBus.dispatch(initiatePaymentCommand);
    }
}
