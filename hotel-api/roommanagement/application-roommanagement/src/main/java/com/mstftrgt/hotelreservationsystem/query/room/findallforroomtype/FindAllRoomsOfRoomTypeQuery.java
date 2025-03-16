package com.mstftrgt.hotelreservationsystem.query.room.findallforroomtype;

import com.mstftrgt.hotelreservationsystem.Query;
import java.util.UUID;

public record FindAllRoomsOfRoomTypeQuery(
        UUID roomTypeId
) implements Query {
}
