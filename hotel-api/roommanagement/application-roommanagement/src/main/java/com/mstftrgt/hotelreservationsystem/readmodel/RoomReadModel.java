package com.mstftrgt.hotelreservationsystem.readmodel;

import com.mstftrgt.hotelreservationsystem.model.Room;
import lombok.Builder;


@Builder
public record RoomReadModel(
        Long id,
        Long roomTypeId,
        String roomNumber
) {

    public static RoomReadModel from(Room room) {
        return RoomReadModel.builder()
                .id(room.getId())
                .roomTypeId(room.getRoomTypeId())
                .roomNumber(room.getRoomNumber())
                .build();
    }

}
