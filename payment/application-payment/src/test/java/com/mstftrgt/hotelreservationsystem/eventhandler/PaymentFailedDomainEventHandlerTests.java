package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedIntegrationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentFailedDomainEventHandlerTests {

    @Mock
    ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    PaymentFailedDomainEventHandler handler;

    @Test
    void shouldHandlePaymentFailedDomainEventSuccessfully() {
        PaymentFailedDomainEvent event = ApplicationTestDataFactory.getPaymentFailedDomainEvent();

        handler.handleEvent(event);

        verify(applicationEventPublisher).publishEvent(any(PaymentFailedIntegrationEvent.class));
    }

}
