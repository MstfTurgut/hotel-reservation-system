package com.mstftrgt.hotelreservationsystem.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RoomTypeModify(
        String title,
        String description,
        BigDecimal priceForNight,
        int adultCapacity,
        int childCapacity
) {
}
