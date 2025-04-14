package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.reservation.rollback.RollbackReservationCommand;
import com.mstftrgt.hotelreservationsystem.cqrs.CommandBus;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedIntegrationEvent;
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
        PaymentFailedIntegrationEvent event = ApplicationTestDataFactory
                .getPaymentFailedIntegrationEvent();

        handler.handle(event);

        verify(commandBus).dispatch(any(RollbackReservationCommand.class));
    }
}
