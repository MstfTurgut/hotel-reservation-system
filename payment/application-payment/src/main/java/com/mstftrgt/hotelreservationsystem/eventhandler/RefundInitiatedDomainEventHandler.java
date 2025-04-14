package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.DomainEventHandler;
import com.mstftrgt.hotelreservationsystem.IntegrationEventPublisher;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundInitiatedDomainEventHandler extends DomainEventHandler<RefundInitiatedDomainEvent> {

    private final IntegrationEventPublisher integrationEventPublisher;

    @Override
    public void handleEvent(RefundInitiatedDomainEvent event) {

        RefundInitiatedIntegrationEvent refundInitiatedIntegrationEvent = RefundInitiatedIntegrationEvent
                .builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .build();

        integrationEventPublisher.publish(refundInitiatedIntegrationEvent);
    }
}
