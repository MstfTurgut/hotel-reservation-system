package com.mstftrgt.hotelreservationsystem.command.notification.send;

import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.notification.dto.NotificationCreate;
import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;
import lombok.Builder;
import java.util.UUID;


import java.math.BigDecimal;

@Builder
public record SendNotificationCommand(UUID reservationId, NotificationType notificationType,
                                      BigDecimal paymentAmount) implements Command {

    public NotificationCreate toNotificationCreateWith(String content) {
        return NotificationCreate.builder()
                .reservationId(reservationId)
                .content(content)
                .type(notificationType)
                .build();
    }
}