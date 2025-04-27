package com.mstftrgt.hotelreservationsystem.query.roomtype.findall;

import com.mstftrgt.hotelreservationsystem.generic.application.Query;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomTypeReadModel;
import lombok.Builder;

import java.util.List;

@Builder
public record FindAllRoomTypesQuery() implements Query<List<RoomTypeReadModel>> {
}
