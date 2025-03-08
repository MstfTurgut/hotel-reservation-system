package com.mstftrgt.hotelreservationsystem.command.notification.send.command.notification.send;

import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;

@Data
@Builder
public class SendNotificationCommand implements Command {
    private final Long reservationId;
    private final NotificationType notificationType;
    private final BigDecimal paymentAmount;
}