package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.DomainEventHandler;
import com.mstftrgt.hotelreservationsystem.IntegrationEventPublisher;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCompletedDomainEventHandler implements DomainEventHandler<PaymentCompletedDomainEvent> {

    private final IntegrationEventPublisher integrationEventPublisher;

    @Override
    public void handle(PaymentCompletedDomainEvent event) {

        PaymentCompletedIntegrationEvent paymentCompletedIntegrationEvent = PaymentCompletedIntegrationEvent.builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .build();

        integrationEventPublisher.publish(paymentCompletedIntegrationEvent);
    }
}
