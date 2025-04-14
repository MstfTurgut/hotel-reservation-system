package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommand;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundFailedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;

import java.math.BigDecimal;
import java.util.UUID;

public class ApplicationTestDataFactory {

    public static SendNotificationCommand getSendNotificationTestCommand() {
        return SendNotificationCommand.builder()
                .notificationType(NotificationType.PAYMENT_FAILED)
                .paymentAmount(BigDecimal.TEN)
                .reservationId(UUID.randomUUID())
                .build();
    }

    public static PaymentCompletedIntegrationEvent getPaymentCompletedIntegrationEvent() {
        return PaymentCompletedIntegrationEvent.builder()
                .paymentAmount(BigDecimal.TEN)
                .reservationId(UUID.randomUUID())
                .build();
    }

    public static PaymentFailedIntegrationEvent getPaymentFailedIntegrationEvent() {
        return PaymentFailedIntegrationEvent.builder()
                .paymentAmount(BigDecimal.TEN)
                .reservationId(UUID.randomUUID())
                .build();
    }

    public static RefundInitiatedIntegrationEvent getRefundInitiatedIntegrationEvent() {
        return RefundInitiatedIntegrationEvent.builder()
                .paymentAmount(BigDecimal.TEN)
                .reservationId(UUID.randomUUID())
                .build();
    }

    public static RefundFailedIntegrationEvent getRefundFailedIntegrationEvent() {
        return RefundFailedIntegrationEvent.builder()
                .paymentAmount(BigDecimal.TEN)
                .reservationId(UUID.randomUUID())
                .build();
    }
}
