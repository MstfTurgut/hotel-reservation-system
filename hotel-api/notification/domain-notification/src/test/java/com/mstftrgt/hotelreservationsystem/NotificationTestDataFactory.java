package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.notification.dto.NotificationCreate;
import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;

import java.util.UUID;

public class NotificationTestDataFactory {

    public static NotificationCreate getTestNotificationCreate() {
        return NotificationCreate.builder()
                .reservationId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .content("testContent")
                .type(NotificationType.PAYMENT_CREATED)
                .build();
    }
}
