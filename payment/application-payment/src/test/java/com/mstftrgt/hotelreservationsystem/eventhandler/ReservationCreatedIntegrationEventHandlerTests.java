package com.mstftrgt.hotelreservationsystem.eventhandler;


import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.payment.initiate.InitiatePaymentCommand;
import com.mstftrgt.hotelreservationsystem.cqrs.CommandBus;
import com.mstftrgt.hotelreservationsystem.event.ReservationCreatedIntegrationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReservationCreatedIntegrationEventHandlerTests {

    @Mock
    CommandBus commandBus;

    @InjectMocks
    ReservationCreatedIntegrationEventHandler handler;

    @Test
    void shouldHandleReservationCreatedIntegrationEventSuccessfully() {
        ReservationCreatedIntegrationEvent event = ApplicationTestDataFactory
                .getReservationCreatedIntegrationEvent();

        handler.handle(event);

        verify(commandBus).dispatch(any(InitiatePaymentCommand.class));
    }

}
