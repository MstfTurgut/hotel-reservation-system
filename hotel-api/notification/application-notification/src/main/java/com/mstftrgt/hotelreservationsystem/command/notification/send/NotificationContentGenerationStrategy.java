package com.mstftrgt.hotelreservationsystem.command.notification.send;




import com.mstftrgt.hotelreservationsystem.facade.ReservationInfoContract;

import java.math.BigDecimal;

public interface NotificationContentGenerationStrategy {

    String generateContent(ReservationInfoContract reservationInfo, BigDecimal paymentAmount);
}
