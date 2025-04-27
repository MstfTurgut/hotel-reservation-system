package com.mstftrgt.hotelreservationsystem.eventhandler;


import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.payment.processinhotel.ProcessInHotelPaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.processonline.ProcessOnlinePaymentCommand;
import com.mstftrgt.hotelreservationsystem.event.ReservationCreatedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
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
    void shouldDispatchProcessInHotelPaymentCommandWhenCardDetailsIsNull() {
        ReservationCreatedIntegrationEvent event = ApplicationTestDataFactory
                .getReservationCreatedIntegrationEvent().withCardDetails(null);

        handler.handle(event);

        verify(commandBus).dispatch(any(ProcessInHotelPaymentCommand.class));
    }

    @Test
    void shouldDispatchProcessInHotelPaymentCommandWhenCardDetailsIsNotNull() {
        ReservationCreatedIntegrationEvent event = ApplicationTestDataFactory.getReservationCreatedIntegrationEvent();

        handler.handle(event);

        verify(commandBus).dispatch(any(ProcessOnlinePaymentCommand.class));
    }

}
