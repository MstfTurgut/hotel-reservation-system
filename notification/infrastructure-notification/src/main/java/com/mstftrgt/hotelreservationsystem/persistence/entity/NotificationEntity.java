package com.mstftrgt.hotelreservationsystem.persistence.entity;

import com.mstftrgt.hotelreservationsystem.notification.model.Notification;
import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;
import lombok.Data;
import lombok.Builder;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification", schema = "notification")
public class NotificationEntity {

    @Id
    private UUID id;
    private UUID reservationId;
    private String content;
    private LocalDateTime createdAt;
    private String type;

    public static NotificationEntity from(Notification notification) {
        return NotificationEntity.builder()
                .id(notification.getId())
                .reservationId(notification.getReservationId())
                .content(notification.getContent())
                .createdAt(notification.getCreatedAt())
                .type(notification.getType().name())
                .build();
    }

    public Notification toModel() {
        return Notification.builder()
                .id(id)
                .reservationId(reservationId)
                .content(content)
                .createdAt(createdAt)
                .type(NotificationType.valueOf(type))
                .build();
    }
}
