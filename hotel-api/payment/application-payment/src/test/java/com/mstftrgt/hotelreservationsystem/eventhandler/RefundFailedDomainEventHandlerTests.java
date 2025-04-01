package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.IntegrationEventPublisher;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundFailedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundFailedIntegrationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RefundFailedDomainEventHandlerTests {

    @Mock
    IntegrationEventPublisher integrationEventPublisher;

    @InjectMocks
    RefundFailedDomainEventHandler handler;

    @Test
    void shouldHandleRefundFailedDomainEventSuccessfully() {
        RefundFailedDomainEvent event = ApplicationTestDataFactory.getRefundFailedDomainEvent();

        handler.handle(event);

        verify(integrationEventPublisher).publish(any(RefundFailedIntegrationEvent.class));
    }

}
