package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefundInitiatedDomainEventHandler {

    private final ApplicationEventPublisher applicationEventPublisher;

    @EventListener
    public void handleEvent(RefundInitiatedDomainEvent event) {
        log.info("Handling domain event: {}", event);

        RefundInitiatedIntegrationEvent refundInitiatedIntegrationEvent = RefundInitiatedIntegrationEvent
                .builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .build();

        applicationEventPublisher.publishEvent(refundInitiatedIntegrationEvent);
    }
}
