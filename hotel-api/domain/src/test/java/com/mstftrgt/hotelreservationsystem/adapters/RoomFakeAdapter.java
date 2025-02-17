package com.mstftrgt.hotelreservationsystem.adapters;

import com.mstftrgt.hotelreservationsystem.room.model.Room;
import com.mstftrgt.hotelreservationsystem.room.port.RoomPort;

import java.util.Optional;

public class RoomFakeAdapter implements RoomPort {


    private static final long ABSENT_ROOM_ID = 9999L;
    private boolean isCalled;


    public RoomFakeAdapter() {
        this.isCalled = false;
    }

    public boolean isCalled() {
        return isCalled;
    }

    @Override
    public Room save(Room room) {
        isCalled = true;

        return Room.builder()
                .id(1L)
                .roomTypeId(room.getRoomTypeId())
                .roomNumber(room.getRoomNumber())
                .build();
    }

    @Override
    public void deleteById(long roomId) {
        isCalled = true;
    }

    @Override
    public Optional<Room> findById(long roomId) {
        isCalled = true;

        if(ABSENT_ROOM_ID == roomId) {
            return Optional.empty();
        }

        return Optional.of(Room.builder()
                .id(roomId)
                .roomTypeId(1L)
                .roomNumber("101")
                .build());
    }
}
