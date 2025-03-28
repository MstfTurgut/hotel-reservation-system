package com.mstftrgt.hotelreservationsystem.facade;

import com.mstftrgt.hotelreservationsystem.model.RoomType;
import lombok.Builder;
import java.util.UUID;

import java.math.BigDecimal;

@Builder
public record RoomTypeContract(
        UUID id,
        String title,
        String description,
        BigDecimal priceForNight,
        int numberOfRooms,
        int adultCapacity,
        int childCapacity
) {

    public static RoomTypeContract from(RoomType roomType) {
        return RoomTypeContract.builder()
                .id(roomType.getId())
                .title(roomType.getTitle())
                .description(roomType.getDescription())
                .priceForNight(roomType.getPriceForNight())
                .numberOfRooms(roomType.getNumberOfRooms())
                .adultCapacity(roomType.getAdultCapacity())
                .childCapacity(roomType.getChildCapacity())
                .build();
    }

}
