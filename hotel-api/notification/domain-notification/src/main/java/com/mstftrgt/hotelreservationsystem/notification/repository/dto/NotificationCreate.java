package com.mstftrgt.hotelreservationsystem.notification.repository.dto;

import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;

public record NotificationCreate(long reservationId, String content, NotificationType type) {
}
