package com.mstftrgt.hotelreservationsystem.readmodel;



import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ReservationAvailabilityForRoomTypeReadModel(
        String roomTypeTitle,

        String roomTypeDescription,

        BigDecimal roomTypePriceForNight,

        int roomTypeAdultCapacity,

        int roomTypeChildCapacity,

        int remainingRooms,
        BigDecimal totalPrice
) {
}
