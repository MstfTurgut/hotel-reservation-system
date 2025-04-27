package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentCompletedDomainEventHandler {

    private final ApplicationEventPublisher applicationEventPublisher;

    @EventListener
    public void handleEvent(PaymentCompletedDomainEvent event) {
        log.info("Handling domain event: {}", event);

        PaymentCompletedIntegrationEvent paymentCompletedIntegrationEvent = PaymentCompletedIntegrationEvent.builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .build();

        applicationEventPublisher.publishEvent(paymentCompletedIntegrationEvent);
    }
}
