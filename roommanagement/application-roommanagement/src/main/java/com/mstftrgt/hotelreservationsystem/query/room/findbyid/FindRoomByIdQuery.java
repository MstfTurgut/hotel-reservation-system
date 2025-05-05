package com.mstftrgt.hotelreservationsystem.query.room.findbyid;

import com.mstftrgt.hotelreservationsystem.generic.application.Query;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomReadModel;

import java.util.UUID;

public record FindRoomByIdQuery(
        UUID roomId
) implements Query<RoomReadModel> {
}
