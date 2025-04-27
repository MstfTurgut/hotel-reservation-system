package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedIntegrationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentCompletedDomainEventHandlerTests {

    @Mock
    ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    PaymentCompletedDomainEventHandler handler;

    @Test
    void shouldHandlePaymentCompletedDomainEventSuccessfully() {
        PaymentCompletedDomainEvent event = ApplicationTestDataFactory.getPaymentCompletedDomainEvent();

        handler.handleEvent(event);

        verify(applicationEventPublisher).publishEvent(any(PaymentCompletedIntegrationEvent.class));
    }
}
