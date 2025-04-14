package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.IntegrationEventHandler;
import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommand;
import com.mstftrgt.hotelreservationsystem.cqrs.CommandBus;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundInitiatedIntegrationEventHandler extends IntegrationEventHandler<RefundInitiatedIntegrationEvent> {

    private final CommandBus commandBus;

    @Override
    public void handle(RefundInitiatedIntegrationEvent event) {
        SendNotificationCommand sendNotificationCommand = SendNotificationCommand.builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .notificationType(NotificationType.REFUND_INITIATED)
                .build();

        commandBus.dispatch(sendNotificationCommand);
    }
}
