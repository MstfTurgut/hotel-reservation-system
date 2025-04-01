package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.IntegrationEventPublisher;
import com.mstftrgt.hotelreservationsystem.event.ReservationCancelledIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.event.ReservationCreatedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.eventhandler.ReservationCancelledDomainEventHandler;
import com.mstftrgt.hotelreservationsystem.eventhandler.ReservationCreatedDomainEventHandler;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCancelledDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCreatedDomainEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReservationCreatedDomainEventHandlerTests {

    @Mock
    IntegrationEventPublisher integrationEventPublisher;

    @InjectMocks
    ReservationCreatedDomainEventHandler handler;

    @Test
    void shouldHandleReservationCreatedDomainEventSuccessfully() {
        ReservationCreatedDomainEvent event = ApplicationTestDataFactory.getReservationCreatedDomainEvent();

        handler.handle(event);

        verify(integrationEventPublisher).publish(any(ReservationCreatedIntegrationEvent.class));
    }

}
