package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.DomainEventHandler;
import com.mstftrgt.hotelreservationsystem.IntegrationEventPublisher;
import com.mstftrgt.hotelreservationsystem.event.RefundFailedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundFailedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundFailedDomainEventHandler implements DomainEventHandler<RefundFailedDomainEvent> {

    private final IntegrationEventPublisher integrationEventPublisher;

    @Override
    public void handle(RefundFailedDomainEvent event) {

        RefundFailedIntegrationEvent refundFailedIntegrationEvent = RefundFailedIntegrationEvent
                .builder()
                .reservationId(event.reservationId())
                .paymentAmount(event.paymentAmount())
                .build();

        integrationEventPublisher.publish(refundFailedIntegrationEvent);
    }
}
