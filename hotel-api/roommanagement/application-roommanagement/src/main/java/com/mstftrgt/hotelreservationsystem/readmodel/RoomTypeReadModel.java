package com.mstftrgt.hotelreservationsystem.readmodel;

import com.mstftrgt.hotelreservationsystem.model.RoomType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RoomTypeReadModel(
        Long id,
        String title,
        String description,
        BigDecimal priceForNight,
        int numberOfRooms,
        int adultCapacity,
        int childCapacity
) {

    public static RoomTypeReadModel from(RoomType roomType) {
        return RoomTypeReadModel.builder()
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
