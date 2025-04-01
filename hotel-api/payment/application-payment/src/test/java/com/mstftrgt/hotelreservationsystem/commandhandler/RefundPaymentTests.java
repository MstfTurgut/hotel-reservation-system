package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.payment.refund.RefundPaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.refund.RefundPaymentCommandHandler;
import com.mstftrgt.hotelreservationsystem.exception.PaymentNotFoundException;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.port.PaymentGatewayPort;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
public class RefundPaymentTests {

    @Mock
    Payment payment;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    PaymentGatewayPort paymentGatewayPort;

    @InjectMocks
    RefundPaymentCommandHandler handler;


    @Test
    void shouldRefundPaymentSuccessfully() {
        RefundPaymentCommand command = ApplicationTestDataFactory.getRefundPaymentTestCommand();

        when(paymentRepository.findByReservationId(command.reservationId())).thenReturn(Optional.of(payment));

        handler.handle(command);

        verify(paymentGatewayPort).initiateRefund(payment.getTransactionId());
        verify(payment).markAsRefundInitiated();
        verify(paymentRepository).save(payment);
    }

    @Test
    void shouldThrowIfPaymentNotFound() {
        RefundPaymentCommand command = ApplicationTestDataFactory.getRefundPaymentTestCommand();

        when(paymentRepository.findByReservationId(command.reservationId())).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> handler.handle(command));

        verifyNoInteractions(paymentGatewayPort);
        verifyNoInteractions(payment);
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void shouldMarkAsRefundFailedIfInitiateRefundThrows() {
        RefundPaymentCommand command = ApplicationTestDataFactory.getRefundPaymentTestCommand();

        when(paymentRepository.findByReservationId(command.reservationId())).thenReturn(Optional.of(payment));
        doThrow(RuntimeException.class).when(paymentGatewayPort).initiateRefund(any(UUID.class));

        handler.handle(command);

        verify(payment).markAsRefundFailed();
        verify(paymentRepository).save(payment);
    }
}
