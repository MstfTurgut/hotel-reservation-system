package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.DomainEventHandler;
import com.mstftrgt.hotelreservationsystem.IntegrationEventPublisher;
import com.mstftrgt.hotelreservationsystem.event.ReservationCreatedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCreatedDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCreatedDomainEventHandler extends DomainEventHandler<ReservationCreatedDomainEvent> {

    private final IntegrationEventPublisher integrationEventPublisher;

    @Override
    public void handleEvent(ReservationCreatedDomainEvent event) {

        ReservationCreatedIntegrationEvent reservationCreatedIntegrationEvent = ReservationCreatedIntegrationEvent
                .builder()
                .totalPrice(event.totalPrice())
                .reservationId(event.reservationId())
                .cardDetails(event.cardDetails())
                .build();

        integrationEventPublisher.publish(reservationCreatedIntegrationEvent);
    }
}
