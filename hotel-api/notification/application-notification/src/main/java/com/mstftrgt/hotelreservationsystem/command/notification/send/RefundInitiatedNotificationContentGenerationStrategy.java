package com.mstftrgt.hotelreservationsystem.command.notification.send;


import com.mstftrgt.hotelreservationsystem.facade.ReservationInfoContract;

import java.math.BigDecimal;

public class RefundInitiatedNotificationContentGenerationStrategy implements NotificationContentGenerationStrategy{

    @Override
    public String generateContent(ReservationInfoContract reservationInfo, BigDecimal paymentAmount) {
        return String.format(
                        "Dear %s,\n\n" +
                        "We have initiated a refund of $%.2f for your reservation (%s) at Imaginary Hotel. " +
                        "The refund should be processed within 5-7 business days, " +
                        "depending on your financial institution.\n\n" +
                        "Thank you for your understanding.",
                reservationInfo.customerFullName(),
                paymentAmount,
                reservationInfo.reservationCode()
        );
    }
}
