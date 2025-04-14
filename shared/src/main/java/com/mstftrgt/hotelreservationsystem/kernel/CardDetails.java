package com.mstftrgt.hotelreservationsystem.kernel;

import lombok.Builder;

@Builder
public record CardDetails(
        String cardNumber,
        String cardHolderName,
        String expiryDate,
        String cvv
) {
}
