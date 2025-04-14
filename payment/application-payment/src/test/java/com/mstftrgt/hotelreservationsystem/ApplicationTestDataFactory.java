package com.mstftrgt.hotelreservationsystem;


import com.mstftrgt.hotelreservationsystem.command.payment.initiate.InitiatePaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.processinhotel.ProcessInHotelPaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.processonline.ProcessOnlinePaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.refund.RefundPaymentCommand;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentInitiatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundFailedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.ReservationCancelledIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.event.ReservationCreatedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.eventhandler.ReservationCancelledIntegrationEventHandler;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.model.PaymentStatus;
import com.mstftrgt.hotelreservationsystem.query.payment.findforreservation.FindPaymentForReservationQuery;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ApplicationTestDataFactory {

    public static InitiatePaymentCommand getInitiatePaymentTestCommand() {
        return InitiatePaymentCommand.builder()
                .cardDetails(CardDetails.builder()
                        .cardNumber("0000000000000000")
                        .cardHolderName("testName")
                        .expiryDate("12/25")
                        .cvv("000")
                        .build())
                .paymentAmount(BigDecimal.TEN)
                .reservationId(UUID.randomUUID())
                .build();
    }

    public static ProcessInHotelPaymentCommand getProcessInHotelPaymentTestCommand() {
        return ProcessInHotelPaymentCommand.builder()
                .paymentId(UUID.randomUUID())
                .build();
    }

    public static ProcessOnlinePaymentCommand getProcessOnlinePaymentTestCommand() {
        return ProcessOnlinePaymentCommand.builder()
                .cardDetails(CardDetails.builder()
                        .cardNumber("0000000000000000")
                        .cardHolderName("testName")
                        .expiryDate("12/25")
                        .cvv("000")
                        .build())
                .paymentId(UUID.randomUUID())
                .build();
    }

    public static RefundPaymentCommand getRefundPaymentTestCommand() {
        return RefundPaymentCommand.builder()
                .reservationId(UUID.randomUUID())
                .build();
    }

    public static FindPaymentForReservationQuery getFindPaymentForReservationTestQuery() {
        return FindPaymentForReservationQuery.builder()
                .reservationId(UUID.randomUUID())
                .build();
    }

    public static Payment getTestPayment() {
        return Payment.builder()
                .id(UUID.randomUUID())
                .amount(BigDecimal.TEN)
                .reservationId(UUID.randomUUID())
                .transactionId(UUID.randomUUID())
                .createDate(LocalDateTime.now())
                .status(PaymentStatus.PAID)
                .build();
    }

    public static PaymentCompletedDomainEvent getPaymentCompletedDomainEvent() {
        return PaymentCompletedDomainEvent.builder()
                .reservationId(UUID.randomUUID())
                .paymentAmount(BigDecimal.TEN)
                .build();
    }

    public static PaymentFailedDomainEvent getPaymentFailedDomainEvent() {
        return PaymentFailedDomainEvent.builder()
                .reservationId(UUID.randomUUID())
                .paymentAmount(BigDecimal.TEN)
                .build();
    }

    public static PaymentInitiatedDomainEvent getPaymentInitiatedDomainEvent() {
        return PaymentInitiatedDomainEvent.builder()
                .paymentId(UUID.randomUUID())
                .cardDetails(CardDetails.builder()
                        .cardNumber("0000000000000000")
                        .cardHolderName("testName")
                        .expiryDate("12/25")
                        .cvv("000")
                        .build())
                .build();
    }

    public static RefundFailedDomainEvent getRefundFailedDomainEvent() {
        return RefundFailedDomainEvent.builder()
                .reservationId(UUID.randomUUID())
                .paymentAmount(BigDecimal.TEN)
                .build();
    }

    public static RefundInitiatedDomainEvent getRefundInitiatedDomainEvent() {
        return RefundInitiatedDomainEvent.builder()
                .reservationId(UUID.randomUUID())
                .paymentAmount(BigDecimal.TEN)
                .build();
    }

    public static ReservationCancelledIntegrationEvent getReservationCancelledIntegrationEvent() {
        return ReservationCancelledIntegrationEvent.builder()
                .reservationId(UUID.randomUUID())
                .build();
    }

    public static ReservationCreatedIntegrationEvent getReservationCreatedIntegrationEvent() {
        return ReservationCreatedIntegrationEvent.builder()
                .totalPrice(BigDecimal.TEN)
                .reservationId(UUID.randomUUID())
                .cardDetails(CardDetails.builder()
                        .cardNumber("0000000000000000")
                        .cardHolderName("testName")
                        .expiryDate("12/25")
                        .cvv("000")
                        .build())
                .build();
    }

}
