package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.dto.RoomTypeCreate;
import com.mstftrgt.hotelreservationsystem.dto.RoomTypeModify;
import com.mstftrgt.hotelreservationsystem.model.RoomType;

import java.math.BigDecimal;
import java.util.UUID;

public class RoomTypeTestDataFactory {

    public static RoomTypeCreate getTestRoomTypeCreate() {
        return RoomTypeCreate.builder()
                .title("testTitle")
                .description("testDescription")
                .priceForNight(BigDecimal.TEN)
                .adultCapacity(2)
                .childCapacity(1)
                .build();
    }

    public static RoomType getTestRoomType() {
        return RoomType.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .title("testTitle")
                .description("testDescription")
                .priceForNight(BigDecimal.TEN)
                .numberOfRooms(0)
                .adultCapacity(2)
                .childCapacity(1)
                .build();
    }

    public static RoomTypeModify getTestRoomTypeModify() {
        return RoomTypeModify.builder()
                .title("testTitleModified")
                .description("testDescriptionModified")
                .priceForNight(BigDecimal.ONE)
                .adultCapacity(1)
                .childCapacity(0)
                .build();
    }

    public static RoomType getTestRoomTypeWithCapacity(int adultCapacity, int childCapacity) {
        return getTestRoomType().withAdultCapacity(adultCapacity).withChildCapacity(childCapacity);
    }
}
