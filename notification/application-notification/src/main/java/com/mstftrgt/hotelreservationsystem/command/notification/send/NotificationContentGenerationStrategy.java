package com.mstftrgt.hotelreservationsystem.command.notification.send;




import com.mstftrgt.hotelreservationsystem.contract.ReservationInfoContract;

import java.math.BigDecimal;

public interface NotificationContentGenerationStrategy {

    String generateContent(ReservationInfoContract reservationInfo, BigDecimal paymentAmount);
}
