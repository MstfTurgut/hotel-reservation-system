package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.event.ReservationCreatedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCreatedDomainEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReservationCreatedDomainEventHandlerTests {

    @Mock
    ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    ReservationCreatedDomainEventHandler handler;

    @Test
    void shouldHandleReservationCreatedDomainEventSuccessfully() {
        ReservationCreatedDomainEvent event = ApplicationTestDataFactory.getReservationCreatedDomainEvent();

        handler.handleEvent(event);

        verify(applicationEventPublisher).publishEvent(any(ReservationCreatedIntegrationEvent.class));
    }

}
