package com.mstftrgt.hotelreservationsystem.query.room.findallforroomtype;

import com.mstftrgt.hotelreservationsystem.Query;
import lombok.Builder;

import java.util.UUID;

@Builder
public record FindAllRoomsOfRoomTypeQuery(
        UUID roomTypeId
) implements Query {
}
