package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.DomainEventHandler;
import com.mstftrgt.hotelreservationsystem.IntegrationEventPublisher;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentFailedDomainEventHandler implements DomainEventHandler<PaymentFailedDomainEvent> {

    private final IntegrationEventPublisher integrationEventPublisher;

    @Override
    public void handle(PaymentFailedDomainEvent event) {

        PaymentFailedIntegrationEvent paymentFailedIntegrationEvent = PaymentFailedIntegrationEvent.builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .build();

        integrationEventPublisher.publish(paymentFailedIntegrationEvent);
    }
}
