package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommand;
import com.mstftrgt.hotelreservationsystem.cqrs.CommandBus;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedIntegrationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RefundInitiatedIntegrationEventHandlerTests {

    @Mock
    CommandBus commandBus;

    @InjectMocks
    RefundInitiatedIntegrationEventHandler handler;

    @Test
    void shouldHandleRefundInitiatedIntegrationEventSuccessfully() {
        RefundInitiatedIntegrationEvent event = ApplicationTestDataFactory.getRefundInitiatedIntegrationEvent();

        handler.handle(event);

        verify(commandBus).dispatch(any(SendNotificationCommand.class));
    }

}
