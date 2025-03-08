package com.mstftrgt.hotelreservationsystem.notification.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Notification {
    private Long id;
    private Long reservationId;
    private String content;
    private LocalDateTime createdAt;
    private NotificationType type;
}
