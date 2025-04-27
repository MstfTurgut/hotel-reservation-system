package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommand;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentFailedIntegrationEventHandlerTests {

    @Mock
    CommandBus commandBus;

    @InjectMocks
    PaymentFailedIntegrationEventHandler handler;

    @Test
    void shouldHandlePaymentFailedIntegrationEventSuccessfully() {
        PaymentFailedIntegrationEvent event = ApplicationTestDataFactory.getPaymentFailedIntegrationEvent();

        handler.handle(event);

        verify(commandBus).dispatch(any(SendNotificationCommand.class));
    }

}
