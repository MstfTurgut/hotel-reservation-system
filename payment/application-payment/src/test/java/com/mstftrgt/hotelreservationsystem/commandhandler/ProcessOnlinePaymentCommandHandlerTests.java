package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.payment.processonline.ProcessOnlinePaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.processonline.ProcessOnlinePaymentCommandHandler;
import com.mstftrgt.hotelreservationsystem.dto.PaymentCreate;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.port.PaymentGatewayPort;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProcessOnlinePaymentCommandHandlerTests {

    @Mock
    Payment payment;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    PaymentGatewayPort paymentGatewayPort;

    @InjectMocks
    ProcessOnlinePaymentCommandHandler handler;


    @Test
    void shouldProcessOnlinePaymentSuccessfully() {
        ProcessOnlinePaymentCommand command = ApplicationTestDataFactory.getProcessOnlinePaymentTestCommand();
        UUID transactionId = UUID.randomUUID();

        try (MockedStatic<Payment> paymentStatic = mockStatic(Payment.class)) {
            paymentStatic.when(() -> Payment.create(any(PaymentCreate.class)))
                    .thenReturn(payment);

            when(paymentGatewayPort.pay(payment.getAmount(), command.cardDetails())).thenReturn(transactionId);

            handler.handle(command);
        }

        verify(payment).setTransactionId(transactionId);
        verify(payment).markAsPaid();
        verify(paymentRepository).save(payment);
    }


    @Test
    void shouldMarkAsFailedIfPayThrows() {
        ProcessOnlinePaymentCommand command = ApplicationTestDataFactory.getProcessOnlinePaymentTestCommand();

        try (MockedStatic<Payment> paymentStatic = mockStatic(Payment.class)) {
            paymentStatic.when(() -> Payment.create(any(PaymentCreate.class)))
                    .thenReturn(payment);

            doThrow(RuntimeException.class).when(paymentGatewayPort).pay(any(BigDecimal.class), any(CardDetails.class));

            handler.handle(command);
        }

        verify(payment).markAsFailed();
        verify(paymentRepository).save(payment);
    }
}
