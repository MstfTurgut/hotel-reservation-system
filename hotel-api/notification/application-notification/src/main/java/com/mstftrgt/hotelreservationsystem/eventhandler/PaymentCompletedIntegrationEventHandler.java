package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.CommandDispatcher;
import com.mstftrgt.hotelreservationsystem.IntegrationEventHandler;
import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommand;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCompletedIntegrationEventHandler implements IntegrationEventHandler<PaymentCompletedIntegrationEvent> {

    private final CommandDispatcher commandDispatcher;

    @Override
    public void handle(PaymentCompletedIntegrationEvent event) {
        SendNotificationCommand sendNotificationCommand = SendNotificationCommand.builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .notificationType(NotificationType.PAYMENT_CREATED)
                .build();

        commandDispatcher.dispatch(sendNotificationCommand);
    }
}
