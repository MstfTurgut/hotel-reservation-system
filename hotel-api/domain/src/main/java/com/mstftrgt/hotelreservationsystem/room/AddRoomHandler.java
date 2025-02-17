package com.mstftrgt.hotelreservationsystem.room;

import com.mstftrgt.hotelreservationsystem.room.message.usecase.AddRoomUseCase;
import com.mstftrgt.hotelreservationsystem.room.model.Room;
import com.mstftrgt.hotelreservationsystem.room.port.RoomPort;
import com.mstftrgt.hotelreservationsystem.roomtype.model.RoomType;
import com.mstftrgt.hotelreservationsystem.roomtype.port.RoomTypePort;
import io.craftgate.modulith.messaging.api.annotation.MessageHandlerConfig;
import io.craftgate.modulith.messaging.api.handler.MessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@MessageHandlerConfig(selector = AddRoomUseCase.class)
public class AddRoomHandler extends MessageHandler<AddRoomUseCase, Room> {

    private final RoomPort roomPort;
    private final RoomTypePort roomTypePort;

    @Override
    @Transactional
    public Room handle(AddRoomUseCase addRoomUseCase) {
        RoomType roomType = getRoomTypeOrElseThrow(addRoomUseCase);
        updateRoomType(roomType);

        Room room = Room.create(addRoomUseCase);
        return roomPort.save(room);
    }

    private void updateRoomType(RoomType roomType) {
        roomType.increaseNumberOfRooms();

        roomTypePort.update(roomType);
    }

    private RoomType getRoomTypeOrElseThrow(AddRoomUseCase addRoomUseCase) {
        return roomTypePort.findById(addRoomUseCase.getRoomTypeId())
                .orElseThrow(() -> new RuntimeException("Room type not found"));
    }
}
