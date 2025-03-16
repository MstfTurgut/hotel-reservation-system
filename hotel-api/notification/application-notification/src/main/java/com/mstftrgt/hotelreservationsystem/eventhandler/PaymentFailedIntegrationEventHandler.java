package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.CommandDispatcher;
import com.mstftrgt.hotelreservationsystem.IntegrationEvent;
import com.mstftrgt.hotelreservationsystem.IntegrationEventHandler;
import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommand;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentFailedIntegrationEventHandler implements IntegrationEventHandler<PaymentFailedIntegrationEvent> {

    private final CommandDispatcher commandDispatcher;

    @Override
    public void handle(PaymentFailedIntegrationEvent event) {
        SendNotificationCommand sendNotificationCommand = SendNotificationCommand.builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .notificationType(NotificationType.PAYMENT_FAILED)
                .build();

        commandDispatcher.dispatch(sendNotificationCommand);
    }
}
