package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.payment.processinhotel.ProcessInHotelPaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.processonline.ProcessOnlinePaymentCommand;
import com.mstftrgt.hotelreservationsystem.cqrs.CommandBus;
import com.mstftrgt.hotelreservationsystem.event.PaymentInitiatedDomainEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentInitiatedDomainEventHandlerTests {

    @Mock
    CommandBus commandBus;

    @InjectMocks
    PaymentInitiatedDomainEventHandler handler;

    @Test
    void shouldDispatchProcessInHotelPaymentCommandWhenCardDetailsIsNull() {
        PaymentInitiatedDomainEvent event = ApplicationTestDataFactory
                .getPaymentInitiatedDomainEvent().withCardDetails(null);

        handler.handleEvent(event);

        verify(commandBus).dispatch(any(ProcessInHotelPaymentCommand.class));
    }

    @Test
    void shouldDispatchProcessInHotelPaymentCommandWhenCardDetailsIsNotNull() {
        PaymentInitiatedDomainEvent event = ApplicationTestDataFactory.getPaymentInitiatedDomainEvent();

        handler.handleEvent(event);

        verify(commandBus).dispatch(any(ProcessOnlinePaymentCommand.class));
    }
}
