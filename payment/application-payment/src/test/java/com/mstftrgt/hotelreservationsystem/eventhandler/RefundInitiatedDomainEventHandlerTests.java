package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedIntegrationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RefundInitiatedDomainEventHandlerTests {

    @Mock
    ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    RefundInitiatedDomainEventHandler handler;

    @Test
    void shouldHandleRefundInitiatedDomainEventSuccessfully() {
        RefundInitiatedDomainEvent event = ApplicationTestDataFactory.getRefundInitiatedDomainEvent();

        handler.handleEvent(event);

        verify(applicationEventPublisher).publishEvent(any(RefundInitiatedIntegrationEvent.class));
    }

}
