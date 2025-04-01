package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.CommandDispatcher;
import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommand;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundFailedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.eventhandler.PaymentCompletedIntegrationEventHandler;
import com.mstftrgt.hotelreservationsystem.eventhandler.RefundFailedIntegrationEventHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RefundFailedIntegrationEventHandlerTests {

    @Mock
    CommandDispatcher commandDispatcher;

    @InjectMocks
    RefundFailedIntegrationEventHandler handler;

    @Test
    void shouldHandleRefundFailedIntegrationEventSuccessfully() {
        RefundFailedIntegrationEvent event = ApplicationTestDataFactory.getRefundFailedIntegrationEvent();

        handler.handle(event);

        verify(commandDispatcher).dispatch(any(SendNotificationCommand.class));
    }

}
