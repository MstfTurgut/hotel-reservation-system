package com.mstftrgt.hotelreservationsystem.command.notification.send;



import com.mstftrgt.hotelreservationsystem.facade.ReservationInfoContract;

import java.math.BigDecimal;

public class PaymentFailedNotificationContentGenerationStrategy implements NotificationContentGenerationStrategy {

    @Override
    public String generateContent(ReservationInfoContract reservationInfo, BigDecimal paymentAmount) {
        return String.format("Dear %s \n\n" +
                "We were unable to process your payment for reservation at Imaginary Hotel. " +
                "Please check your payment details and try again.\n\n" +
                "If you continue experiencing issues, please contact our reservations team at +0212 987 65 43",
                reservationInfo.customerFullName());
    }
}
