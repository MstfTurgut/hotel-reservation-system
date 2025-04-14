package com.mstftrgt.hotelreservationsystem.notificationsender;

import com.mstftrgt.hotelreservationsystem.notification.dto.NotificationSend;
import com.mstftrgt.hotelreservationsystem.notification.port.NotificationSenderPort;
import org.springframework.stereotype.Service;

@Service
public class NotificationSenderAdapter implements NotificationSenderPort {
    @Override
    public void sendNotification(NotificationSend notificationSend) {
        System.out.println("Notification sent: " + notificationSend.content());
    }
}
