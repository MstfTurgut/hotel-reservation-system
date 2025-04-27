package com.mstftrgt.hotelreservationsystem.notification.model;

import com.mstftrgt.hotelreservationsystem.generic.domain.AggregateRoot;
import com.mstftrgt.hotelreservationsystem.notification.dto.NotificationCreate;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Notification extends AggregateRoot {
    private UUID id;
    private UUID reservationId;
    private String content;
    private LocalDateTime createdAt;
    private NotificationType type;

    public static Notification create(NotificationCreate notificationCreate) {
        return Notification.builder()
                .id(UUID.randomUUID())
                .reservationId(notificationCreate.reservationId())
                .content(notificationCreate.content())
                .createdAt(LocalDateTime.now())
                .type(notificationCreate.type())
                .build();
    }

}
