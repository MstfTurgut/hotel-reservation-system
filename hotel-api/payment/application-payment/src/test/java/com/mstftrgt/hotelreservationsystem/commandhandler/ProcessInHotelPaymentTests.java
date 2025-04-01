package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.payment.processinhotel.ProcessInHotelPaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.processinhotel.ProcessInHotelPaymentCommandHandler;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProcessInHotelPaymentTests {

    @Mock
    Payment payment;

    @Mock
    PaymentRepository paymentRepository;


    @InjectMocks
    ProcessInHotelPaymentCommandHandler handler;

    @Test
    void shouldProcessInHotelPaymentSuccessfully() {
        ProcessInHotelPaymentCommand command = ApplicationTestDataFactory.getProcessInHotelPaymentTestCommand();

        when(paymentRepository.findById(command.paymentId())).thenReturn(Optional.of(payment));

        handler.handle(command);

        verify(payment).markAsPaid();
        verify(paymentRepository).save(payment);
    }

    @Test
    void shouldThrowIfPaymentNotFound() {
        ProcessInHotelPaymentCommand command = ApplicationTestDataFactory.getProcessInHotelPaymentTestCommand();

        when(paymentRepository.findById(command.paymentId())).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> handler.handle(command));

        verify(payment, never()).markAsPaid();
        verify(paymentRepository, never()).save(any());
    }
}
