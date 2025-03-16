package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.DomainEventHandler;
import com.mstftrgt.hotelreservationsystem.IntegrationEventPublisher;
import com.mstftrgt.hotelreservationsystem.event.ReservationCancelledIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCancelledDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCancelledDomainEventHandler implements DomainEventHandler<ReservationCancelledDomainEvent> {

    private final IntegrationEventPublisher integrationEventPublisher;

    @Override
    public void handle(ReservationCancelledDomainEvent event) {

        ReservationCancelledIntegrationEvent reservationCancelledIntegrationEvent = ReservationCancelledIntegrationEvent
                .builder()
                .reservationId(event.reservationId())
                .build();

        integrationEventPublisher.publish(reservationCancelledIntegrationEvent);
    }
}
