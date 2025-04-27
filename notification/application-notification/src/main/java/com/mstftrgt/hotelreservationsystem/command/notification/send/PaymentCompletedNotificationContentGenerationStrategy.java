package com.mstftrgt.hotelreservationsystem.command.notification.send;


import com.mstftrgt.hotelreservationsystem.contract.ReservationInfoContract;

import java.math.BigDecimal;

public class PaymentCompletedNotificationContentGenerationStrategy implements NotificationContentGenerationStrategy {

    @Override
    public String generateContent(ReservationInfoContract reservationInfo, BigDecimal paymentAmount) {
        return String.format(
                "Dear %s,\n\n" +
                "Your payment of $%.2f has been successfully processed. Your reservation at Imaginary Hotel is confirmed!\n\n" +
                "Reservation Code: %s\n" +
                "Confirmation Code: %s\n\n" +
                "Reservation Details:\n" +
                "• Check-in: %s\n" +
                "• Check-out: %s\n" +
                "• Room Type: %s\n" +
                "• Guests: %d Adults and %d Children\n\n" +
                "We look forward to welcoming you soon!",
                reservationInfo.customerFullName(),
                paymentAmount,
                reservationInfo.reservationCode(),
                reservationInfo.confirmationCode(),
                reservationInfo.checkInDate(),
                reservationInfo.checkOutDate(),
                reservationInfo.roomTypeTitle(),
                reservationInfo.adultGuestCount(),
                reservationInfo.childGuestCount()
        );
    }
}
