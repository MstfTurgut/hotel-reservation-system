package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.payment.initiate.InitiatePaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.initiate.InitiatePaymentCommandHandler;
import com.mstftrgt.hotelreservationsystem.dto.PaymentCreate;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InitiatePaymentTests {

    @Mock
    Payment payment;

    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    InitiatePaymentCommandHandler handler;

    @Test
    void shouldInitiatePaymentSuccessfully() {
        InitiatePaymentCommand command = ApplicationTestDataFactory.getInitiatePaymentTestCommand();

        try (MockedStatic<Payment> paymentStatic = mockStatic(Payment.class)) {
            paymentStatic.when(() -> Payment.create(any(PaymentCreate.class)))
                    .thenReturn(payment);

            handler.handle(command);
        }

        verify(payment).initiate(any(CardDetails.class));
        verify(paymentRepository).save(payment);
    }

}
