package com.mstftrgt.hotelreservationsystem.query.room.findallforroomtype;

import com.mstftrgt.hotelreservationsystem.generic.application.Query;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomReadModel;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record FindAllRoomsOfRoomTypeQuery(
        UUID roomTypeId
) implements Query<List<RoomReadModel>> {
}
