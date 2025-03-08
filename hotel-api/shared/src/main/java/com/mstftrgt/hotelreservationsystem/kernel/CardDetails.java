package com.mstftrgt.hotelreservationsystem.kernel;

public record CardDetails(
        String cardNumber,
        String cardHolderName,
        String expiryDate,
        String cvv
) {
}
