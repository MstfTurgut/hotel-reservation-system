package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.event.PaymentFailedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentFailedDomainEventHandler {

    private final ApplicationEventPublisher applicationEventPublisher;

    @EventListener
    public void handleEvent(PaymentFailedDomainEvent event) {
        log.info("Handling domain event: {}", event);

        PaymentFailedIntegrationEvent paymentFailedIntegrationEvent = PaymentFailedIntegrationEvent.builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .build();

        applicationEventPublisher.publishEvent(paymentFailedIntegrationEvent);
    }
}
