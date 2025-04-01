package com.mstftrgt.hotelreservationsystem.eventhandler;


import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.IntegrationEventPublisher;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.event.ReservationCancelledIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.eventhandler.ReservationCancelledDomainEventHandler;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCancelledDomainEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReservationCancelledDomainEventHandlerTests {

    @Mock
    IntegrationEventPublisher integrationEventPublisher;

    @InjectMocks
    ReservationCancelledDomainEventHandler handler;

    @Test
    void shouldHandleReservationCancelledDomainEventSuccessfully() {
        ReservationCancelledDomainEvent event = ApplicationTestDataFactory.getReservationCancelledDomainEvent();

        handler.handle(event);

        verify(integrationEventPublisher).publish(any(ReservationCancelledIntegrationEvent.class));
    }

}
