package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.CommandDispatcher;
import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommand;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.eventhandler.PaymentCompletedIntegrationEventHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentCompletedIntegrationEventHandlerTests {

    @Mock
    CommandDispatcher commandDispatcher;

    @InjectMocks
    PaymentCompletedIntegrationEventHandler handler;

    @Test
    void shouldHandlePaymentCompletedIntegrationEventSuccessfully() {
        PaymentCompletedIntegrationEvent event = ApplicationTestDataFactory.getPaymentCompletedIntegrationEvent();

        handler.handle(event);

        verify(commandDispatcher).dispatch(any(SendNotificationCommand.class));
    }

}
