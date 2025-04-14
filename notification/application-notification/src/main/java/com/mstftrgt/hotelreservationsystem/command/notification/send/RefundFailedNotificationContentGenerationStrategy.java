package com.mstftrgt.hotelreservationsystem.command.notification.send;


import com.mstftrgt.hotelreservationsystem.contract.ReservationInfoContract;

import java.math.BigDecimal;

public class RefundFailedNotificationContentGenerationStrategy implements NotificationContentGenerationStrategy {

    @Override
    public String generateContent(ReservationInfoContract reservationInfo, BigDecimal paymentAmount) {
        return String.format("Dear %s,\n\n" +
                             "We were unable to process the refund for your reservation (%s). " +
                             "Please contact our hotel directly at +0212 987 65 43 to resolve this issue.\n\n" +
                             "We apologize for any inconvenience.",
                reservationInfo.customerFullName(),
                reservationInfo.reservationCode());
    }
}
