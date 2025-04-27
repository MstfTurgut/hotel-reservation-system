package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.event.ReservationCancelledIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCancelledDomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationCancelledDomainEventHandler {

    private final ApplicationEventPublisher applicationEventPublisher;

    @EventListener
    public void handleEvent(ReservationCancelledDomainEvent event) {
        log.info("Handling domain event: {}", event);

        ReservationCancelledIntegrationEvent reservationCancelledIntegrationEvent = ReservationCancelledIntegrationEvent
                .builder()
                .reservationId(event.reservationId())
                .build();

        applicationEventPublisher.publishEvent(reservationCancelledIntegrationEvent);
    }
}
