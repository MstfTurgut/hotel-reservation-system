package com.mstftrgt.hotelreservationsystem.notification.port;

import com.mstftrgt.hotelreservationsystem.notification.dto.NotificationSend;

public interface NotificationSenderPort {

    void sendNotification(NotificationSend notificationSend);

}
