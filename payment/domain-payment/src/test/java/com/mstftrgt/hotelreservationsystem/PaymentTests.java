package com.mstftrgt.hotelreservationsystem;


import com.mstftrgt.hotelreservationsystem.dto.PaymentCreate;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentInitiatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundFailedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.exception.PaymentAlreadyRefundedException;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.model.PaymentStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentTests {


    @Test
    void create_ShouldInitializePaymentCorrectly() {
        PaymentCreate paymentCreate = PaymentTestDataFactory.getTestPaymentCreate();

        Payment payment = Payment.create(paymentCreate);

        assertNotNull(payment.getId());
        assertEquals(paymentCreate.reservationId(), payment.getReservationId());
        assertEquals(paymentCreate.amount(), payment.getAmount());
        assertNotNull(payment.getCreateDate());
    }

    @Test
    void initiate_ShouldSetStatusToPendingAndRegisterEvent() {
        Payment payment = PaymentTestDataFactory.getTestPayment();
        CardDetails cardDetails = PaymentTestDataFactory.getTestCardDetails();

        payment.initiate(cardDetails);

        assertEquals(PaymentStatus.PENDING, payment.getStatus());

        assertEquals(1, payment.getDomainEvents().size());

        PaymentInitiatedDomainEvent event = (PaymentInitiatedDomainEvent) payment.getDomainEvents().get(0);

        assertEquals(payment.getId(), event.paymentId());
        assertEquals(cardDetails, event.cardDetails());
    }

    @Test
    void markAsRefundInitiated_ShouldSetStatusAndRegisterEvent() {
        Payment payment = PaymentTestDataFactory.getTestPayment();

        payment.markAsRefundInitiated();

        assertEquals(PaymentStatus.REFUND_INITIATED, payment.getStatus());

        assertEquals(1, payment.getDomainEvents().size());

        RefundInitiatedDomainEvent event = (RefundInitiatedDomainEvent) payment.getDomainEvents().get(0);
        assertEquals(payment.getReservationId(), event.reservationId());
        assertEquals(payment.getAmount(), event.paymentAmount());
    }

    @Test
    void markAsRefundInitiated_WhenAlreadyRefunded_ShouldThrowException() {
        Payment payment = PaymentTestDataFactory.getTestPaymentWith(PaymentStatus.REFUND_INITIATED);

        assertThrows(PaymentAlreadyRefundedException.class, payment::markAsRefundInitiated);
    }

    @Test
    void markAsRefundFailed_ShouldSetStatusAndRegisterEvent() {
        Payment payment = PaymentTestDataFactory.getTestPayment();

        payment.markAsRefundFailed();

        assertEquals(PaymentStatus.REFUND_FAILED, payment.getStatus());

        assertEquals(1, payment.getDomainEvents().size());

        RefundFailedDomainEvent event = (RefundFailedDomainEvent) payment.getDomainEvents().get(0);
        assertEquals(payment.getReservationId(), event.reservationId());
        assertEquals(payment.getAmount(), event.paymentAmount());
    }

    @Test
    void markAsPaid_ShouldSetStatusAndRegisterEvent() {
        Payment payment = PaymentTestDataFactory.getTestPayment();

        payment.markAsPaid();

        assertEquals(PaymentStatus.PAID, payment.getStatus());

        assertEquals(1, payment.getDomainEvents().size());

        PaymentCompletedDomainEvent event = (PaymentCompletedDomainEvent) payment.getDomainEvents().get(0);
        assertEquals(payment.getReservationId(), event.reservationId());
        assertEquals(payment.getAmount(), event.paymentAmount());
    }

    @Test
    void markAsFailed_ShouldSetStatusAndRegisterEvent() {
        Payment payment = PaymentTestDataFactory.getTestPayment();

        payment.markAsFailed();

        assertEquals(PaymentStatus.FAILED, payment.getStatus());

        assertEquals(1, payment.getDomainEvents().size());

        PaymentFailedDomainEvent event = (PaymentFailedDomainEvent) payment.getDomainEvents().get(0);
        assertEquals(payment.getReservationId(), event.reservationId());
        assertEquals(payment.getAmount(), event.paymentAmount());
    }
}