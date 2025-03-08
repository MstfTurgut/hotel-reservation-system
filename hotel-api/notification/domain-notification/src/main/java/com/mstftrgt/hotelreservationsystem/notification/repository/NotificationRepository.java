package com.mstftrgt.hotelreservationsystem.notification.repository;


import com.mstftrgt.hotelreservationsystem.notification.repository.dto.NotificationCreate;

public interface NotificationRepository {
    void save(NotificationCreate notificationCreate);
}
