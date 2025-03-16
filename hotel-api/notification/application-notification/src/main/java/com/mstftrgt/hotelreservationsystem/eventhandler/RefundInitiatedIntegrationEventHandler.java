package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.CommandDispatcher;
import com.mstftrgt.hotelreservationsystem.IntegrationEventHandler;
import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommand;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundInitiatedIntegrationEventHandler implements IntegrationEventHandler<RefundInitiatedIntegrationEvent> {

    private final CommandDispatcher commandDispatcher;

    @Override
    public void handle(RefundInitiatedIntegrationEvent event) {
        SendNotificationCommand sendNotificationCommand = SendNotificationCommand.builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .notificationType(NotificationType.REFUND_INITIATED)
                .build();

        commandDispatcher.dispatch(sendNotificationCommand);
    }
}
