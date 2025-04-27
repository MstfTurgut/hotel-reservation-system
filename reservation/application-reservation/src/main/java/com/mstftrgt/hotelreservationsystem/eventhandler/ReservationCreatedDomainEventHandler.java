package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.event.ReservationCreatedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCreatedDomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationCreatedDomainEventHandler {

    private final ApplicationEventPublisher applicationEventPublisher;

    @EventListener
    public void handleEvent(ReservationCreatedDomainEvent event) {
        log.info("Handling domain event: {}", event);

        ReservationCreatedIntegrationEvent reservationCreatedIntegrationEvent = ReservationCreatedIntegrationEvent
                .builder()
                .totalPrice(event.totalPrice())
                .reservationId(event.reservationId())
                .cardDetails(event.cardDetails())
                .build();

        applicationEventPublisher.publishEvent(reservationCreatedIntegrationEvent);
    }
}
