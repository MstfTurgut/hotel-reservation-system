package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.exception.RoomNotFoundException;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryHandler;
import com.mstftrgt.hotelreservationsystem.query.room.findbyid.FindRoomByIdQuery;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomReadModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.ROOM_NOT_FOUND_ID;

@Service
public class FindRoomByIdFakeQueryHandler implements QueryHandler<FindRoomByIdQuery, RoomReadModel> {

    @Override
    public RoomReadModel handle(FindRoomByIdQuery query) {

        if(ROOM_NOT_FOUND_ID.equals(query.roomId())) {
            throw new RoomNotFoundException(query.roomId());
        }

        return RoomReadModel.builder()
                .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .roomTypeId(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .roomNumber("101")
                .build();
    }
}
