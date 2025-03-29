package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.dto.RoomCreate;
import com.mstftrgt.hotelreservationsystem.model.Room;

import java.util.UUID;

public class RoomTestDataFactory {

    public static RoomCreate getTestRoomCreate() {
        return RoomCreate.builder()
                .roomTypeId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .roomNumber("101")
                .build();
    }

    public static Room getTestRoom() {
        return Room.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .roomTypeId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .roomNumber("101")
                .build();
    }
}
