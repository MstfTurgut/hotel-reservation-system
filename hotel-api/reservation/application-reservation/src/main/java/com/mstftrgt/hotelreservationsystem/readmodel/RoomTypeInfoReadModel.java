package com.mstftrgt.hotelreservationsystem.readmodel;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RoomTypeInfoReadModel(
        String roomTypeTitle,

        String roomTypeDescription,

        BigDecimal roomTypePriceForNight,

        int roomTypeAdultCapacity,

        int roomTypeChildCapacity
) {
}
