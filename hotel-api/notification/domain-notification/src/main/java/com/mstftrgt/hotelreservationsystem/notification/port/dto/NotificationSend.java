package com.mstftrgt.hotelreservationsystem.notification.port.dto;

import lombok.Builder;

@Builder
public record NotificationSend (
        String content,
        String phoneNumber,
        String emailAddress
) {
}
