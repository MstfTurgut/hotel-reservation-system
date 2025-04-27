package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommand;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentCompletedIntegrationEventHandler {

    private final CommandBus commandBus;

    @EventListener
    public void handle(PaymentCompletedIntegrationEvent event) {
        log.info("Handling integration event {}" , event);

        SendNotificationCommand sendNotificationCommand = SendNotificationCommand.builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .notificationType(NotificationType.PAYMENT_CREATED)
                .build();

        commandBus.dispatch(sendNotificationCommand);
    }
}
