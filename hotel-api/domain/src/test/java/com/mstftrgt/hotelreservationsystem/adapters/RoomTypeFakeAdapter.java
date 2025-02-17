package com.mstftrgt.hotelreservationsystem.adapters;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.mstftrgt.hotelreservationsystem.roomtype.model.RoomType;
import com.mstftrgt.hotelreservationsystem.roomtype.port.RoomTypePort;

public class RoomTypeFakeAdapter implements RoomTypePort {

    private static final long ABSENT_ROOM_TYPE_ID = 9999L;

    private boolean isCalled;

    public RoomTypeFakeAdapter() {
        this.isCalled = false;
    }

    public boolean isCalled() {
        return isCalled;
    }

    @Override
    public Optional<RoomType> findById(long roomTypeId) {
        isCalled = true;

        if(ABSENT_ROOM_TYPE_ID == roomTypeId) {
            return Optional.empty();
        }
        return Optional.of(RoomType.builder()
                .id(roomTypeId)
                .title("Room Type 1")
                .description("Room Type 1 Description")
                .priceForNight(BigDecimal.valueOf(100))
                .numberOfRooms(0)
                .adultCapacity(2)
                .childCapacity(1)
                .build());
    }

    @Override
    public List<RoomType> findAll() {
        isCalled = true;

        return null;
    }


    @Override
    public RoomType save(RoomType roomType) {
        isCalled = true;

        return RoomType.builder()
                .id(1L)
                .title(roomType.getTitle())
                .description(roomType.getDescription())
                .priceForNight(roomType.getPriceForNight())
                .numberOfRooms(roomType.getNumberOfRooms())
                .adultCapacity(roomType.getAdultCapacity())
                .childCapacity(roomType.getChildCapacity())
                .build();
    }


    @Override
    public RoomType update(RoomType roomType) {
        isCalled = true;

        return RoomType.builder()
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
