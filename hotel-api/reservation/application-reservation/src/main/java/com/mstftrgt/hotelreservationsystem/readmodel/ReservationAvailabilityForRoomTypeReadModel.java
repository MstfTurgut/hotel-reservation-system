package com.mstftrgt.hotelreservationsystem.readmodel;



import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ReservationAvailabilityForRoomTypeReadModel(
        RoomTypeInfoReadModel roomTypeInfoReadModel,
        int remainingRooms,
        BigDecimal totalPriceForStayDate
) {
}
