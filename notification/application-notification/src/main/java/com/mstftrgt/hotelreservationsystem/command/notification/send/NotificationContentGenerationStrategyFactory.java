package com.mstftrgt.hotelreservationsystem.command.notification.send;

import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;

public class NotificationContentGenerationStrategyFactory {

    public NotificationContentGenerationStrategy getStrategy(NotificationType type) {
        if (type == NotificationType.PAYMENT_CREATED) {
            return new PaymentCreatedNotificationContentGenerationStrategy();
        } else if (type == NotificationType.PAYMENT_FAILED) {
            return new PaymentFailedNotificationContentGenerationStrategy();
        } else if (type == NotificationType.REFUND_INITIATED) {
            return new RefundInitiatedNotificationContentGenerationStrategy();
        } else if (type == NotificationType.REFUND_FAILED) {
            return new RefundFailedNotificationContentGenerationStrategy();
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
