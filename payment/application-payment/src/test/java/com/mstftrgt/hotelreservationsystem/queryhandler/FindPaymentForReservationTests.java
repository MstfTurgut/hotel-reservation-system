package com.mstftrgt.hotelreservationsystem.queryhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.exception.PaymentNotFoundException;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.query.payment.findforreservation.FindPaymentForReservationQuery;
import com.mstftrgt.hotelreservationsystem.query.payment.findforreservation.FindPaymentForReservationQueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.PaymentReadModel;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPaymentForReservationTests {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private FindPaymentForReservationQueryHandler handler;

    @Test
    void shouldReturnPaymentReadModelWhenPaymentExists() {
        FindPaymentForReservationQuery query = ApplicationTestDataFactory.getFindPaymentForReservationTestQuery();

        Payment payment = ApplicationTestDataFactory.getTestPayment();
        PaymentReadModel expectedReadModel = PaymentReadModel.from(payment);

        when(paymentRepository.findByReservationId(query.reservationId())).thenReturn(Optional.of(payment));

        PaymentReadModel result = handler.handle(query);

        assertEquals(expectedReadModel, result);
    }

    @Test
    void shouldThrowPaymentNotFoundExceptionWhenPaymentDoesNotExist() {
        FindPaymentForReservationQuery query = ApplicationTestDataFactory.getFindPaymentForReservationTestQuery();

        when(paymentRepository.findByReservationId(query.reservationId())).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> handler.handle(query));
    }
}