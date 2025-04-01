package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.CommandDispatcher;
import com.mstftrgt.hotelreservationsystem.command.payment.processinhotel.ProcessInHotelPaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.processonline.ProcessOnlinePaymentCommand;
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
    CommandDispatcher commandDispatcher;

    @InjectMocks
    PaymentInitiatedDomainEventHandler handler;

    @Test
    void shouldDispatchProcessInHotelPaymentCommandWhenCardDetailsIsNull() {
        PaymentInitiatedDomainEvent event = ApplicationTestDataFactory
                .getPaymentInitiatedDomainEvent().withCardDetails(null);

        handler.handle(event);

        verify(commandDispatcher).dispatch(any(ProcessInHotelPaymentCommand.class));
    }

    @Test
    void shouldDispatchProcessInHotelPaymentCommandWhenCardDetailsIsNotNull() {
        PaymentInitiatedDomainEvent event = ApplicationTestDataFactory.getPaymentInitiatedDomainEvent();

        handler.handle(event);

        verify(commandDispatcher).dispatch(any(ProcessOnlinePaymentCommand.class));
    }
}
