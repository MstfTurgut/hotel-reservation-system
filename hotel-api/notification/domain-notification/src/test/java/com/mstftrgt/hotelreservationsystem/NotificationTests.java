package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.notification.dto.NotificationCreate;
import com.mstftrgt.hotelreservationsystem.notification.model.Notification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NotificationTests {

    @Test
    void create_ShouldCreateNotification() {
        NotificationCreate notificationCreate = NotificationTestDataFactory.getTestNotificationCreate();

        Notification notification = Notification.create(notificationCreate);

        assertNotNull(notification.getId());
        assertEquals(notificationCreate.reservationId(), notification.getReservationId());
        assertEquals(notificationCreate.content(), notification.getContent());
        assertNotNull(notification.getCreatedAt());
        assertEquals(notificationCreate.type(), notification.getType());
    }
}
