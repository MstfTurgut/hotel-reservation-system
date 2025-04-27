package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.event.RefundFailedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundFailedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefundFailedDomainEventHandler {

    private final ApplicationEventPublisher applicationEventPublisher;

    @EventListener
    public void handleEvent(RefundFailedDomainEvent event) {
        log.info("Handling domain event: {}", event);

        RefundFailedIntegrationEvent refundFailedIntegrationEvent = RefundFailedIntegrationEvent
                .builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .build();

        applicationEventPublisher.publishEvent(refundFailedIntegrationEvent);
    }
}
