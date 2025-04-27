package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.payment.processinhotel.ProcessInHotelPaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.processinhotel.ProcessInHotelPaymentCommandHandler;
import com.mstftrgt.hotelreservationsystem.dto.PaymentCreate;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProcessInHotelPaymentCommandHandlerTests {

    @Mock
    Payment payment;

    @Mock
    PaymentRepository paymentRepository;


    @InjectMocks
    ProcessInHotelPaymentCommandHandler handler;

    @Test
    void shouldProcessInHotelPaymentSuccessfully() {
        ProcessInHotelPaymentCommand command = ApplicationTestDataFactory.getProcessInHotelPaymentTestCommand();

        try (MockedStatic<Payment> paymentStatic = mockStatic(Payment.class)) {
            paymentStatic.when(() -> Payment.create(any(PaymentCreate.class)))
                    .thenReturn(payment);

            handler.handle(command);
        }

        verify(payment).markAsPaid();
        verify(paymentRepository).save(payment);
    }
}
