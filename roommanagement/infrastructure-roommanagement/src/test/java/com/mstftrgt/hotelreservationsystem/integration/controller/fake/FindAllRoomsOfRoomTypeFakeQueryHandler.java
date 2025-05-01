package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.generic.application.QueryHandler;
import com.mstftrgt.hotelreservationsystem.query.room.findallforroomtype.FindAllRoomsOfRoomTypeQuery;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomReadModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FindAllRoomsOfRoomTypeFakeQueryHandler implements QueryHandler<FindAllRoomsOfRoomTypeQuery, List<RoomReadModel>> {

    @Override
    public List<RoomReadModel> handle(FindAllRoomsOfRoomTypeQuery query) {
        return List.of(
                RoomReadModel.builder()
                        .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                        .roomTypeId(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                        .roomNumber("101")
                        .build()
        );
    }
}
