package com.mstftrgt.hotelreservationsystem.query.room.findallforroomtype;

import com.mstftrgt.hotelreservationsystem.Query;

public record FindAllRoomsOfRoomTypeQuery(
        Long roomTypeId
) implements Query {
}
