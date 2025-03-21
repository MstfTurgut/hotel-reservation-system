package com.mstftrgt.hotelreservationsystem.notification.dto;

import lombok.Builder;

@Builder
public record NotificationSend (
        String content,
        String phoneNumber,
        String emailAddress
) {
}
