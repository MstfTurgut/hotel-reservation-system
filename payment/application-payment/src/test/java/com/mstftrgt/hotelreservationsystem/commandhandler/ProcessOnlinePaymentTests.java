package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.payment.processonline.ProcessOnlinePaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.processonline.ProcessOnlinePaymentCommandHandler;
import com.mstftrgt.hotelreservationsystem.exception.PaymentNotFoundException;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.port.PaymentGatewayPort;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProcessOnlinePaymentTests {

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

        when(paymentRepository.findById(command.paymentId())).thenReturn(Optional.of(payment));
        when(paymentGatewayPort.pay(payment.getAmount(), command.cardDetails())).thenReturn(transactionId);

        handler.handle(command);

        verify(payment).setTransactionId(transactionId);
        verify(payment).markAsPaid();
        verify(paymentRepository).save(payment);
    }

    @Test
    void shouldThrowIfPaymentNotFound() {
        ProcessOnlinePaymentCommand command = ApplicationTestDataFactory.getProcessOnlinePaymentTestCommand();

        when(paymentRepository.findById(command.paymentId())).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> handler.handle(command));

        verifyNoInteractions(paymentGatewayPort);
        verifyNoInteractions(payment);
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void shouldMarkAsFailedIfPayThrows() {
        ProcessOnlinePaymentCommand command = ApplicationTestDataFactory.getProcessOnlinePaymentTestCommand();

        when(paymentRepository.findById(command.paymentId())).thenReturn(Optional.of(payment));
        doThrow(RuntimeException.class).when(paymentGatewayPort).pay(any(BigDecimal.class), any(CardDetails.class));

        handler.handle(command);

        verify(payment).markAsFailed();
        verify(paymentRepository).save(payment);
    }
}
