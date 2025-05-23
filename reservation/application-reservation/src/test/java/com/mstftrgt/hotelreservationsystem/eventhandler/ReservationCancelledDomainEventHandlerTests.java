package com.mstftrgt.hotelreservationsystem.eventhandler;


import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.event.ReservationCancelledIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCancelledDomainEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReservationCancelledDomainEventHandlerTests {

    @Mock
    ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    ReservationCancelledDomainEventHandler handler;

    @Test
    void shouldHandleReservationCancelledDomainEventSuccessfully() {
        ReservationCancelledDomainEvent event = ApplicationTestDataFactory.getReservationCancelledDomainEvent();

        handler.handleEvent(event);

        verify(applicationEventPublisher).publishEvent(any(ReservationCancelledIntegrationEvent.class));
    }

}