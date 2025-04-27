package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.payment.refund.RefundPaymentCommand;
import com.mstftrgt.hotelreservationsystem.event.ReservationCancelledIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReservationCancelledIntegrationEventHandlerTests {

    @Mock
    CommandBus commandBus;

    @InjectMocks
    ReservationCancelledIntegrationEventHandler handler;

    @Test
    void shouldHandleReservationCancelledIntegrationEventSuccessfully() {
        ReservationCancelledIntegrationEvent event = ApplicationTestDataFactory
                .getReservationCancelledIntegrationEvent();

        handler.handle(event);

        verify(commandBus).dispatch(any(RefundPaymentCommand.class));
    }

}
