package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommand;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefundInitiatedIntegrationEventHandler {

    private final CommandBus commandBus;

    @EventListener
    public void handle(RefundInitiatedIntegrationEvent event) {
        log.info("Handling integration event {}" , event);

        SendNotificationCommand sendNotificationCommand = SendNotificationCommand.builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .notificationType(NotificationType.REFUND_INITIATED)
                .build();

        commandBus.dispatch(sendNotificationCommand);
    }
}
