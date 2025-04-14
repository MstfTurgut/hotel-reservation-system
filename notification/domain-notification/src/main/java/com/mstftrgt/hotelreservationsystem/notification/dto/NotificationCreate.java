package com.mstftrgt.hotelreservationsystem.notification.dto;

import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;
import lombok.Builder;

import java.util.UUID;

@Builder
public record NotificationCreate(
        UUID reservationId,
        String content,
        NotificationType type) {
}
