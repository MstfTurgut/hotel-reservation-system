package com.mstftrgt.hotelreservationsystem.room;

import com.mstftrgt.hotelreservationsystem.room.message.usecase.DeleteRoomUseCase;
import com.mstftrgt.hotelreservationsystem.room.model.Room;
import com.mstftrgt.hotelreservationsystem.room.port.RoomPort;
import com.mstftrgt.hotelreservationsystem.roomtype.model.RoomType;
import com.mstftrgt.hotelreservationsystem.roomtype.port.RoomTypePort;
import io.craftgate.modulith.messaging.api.annotation.MessageHandlerConfig;
import io.craftgate.modulith.messaging.api.handler.VoidMessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@MessageHandlerConfig(selector = DeleteRoomUseCase.class)
public class DeleteRoomHandler extends VoidMessageHandler<DeleteRoomUseCase> {

    private final RoomPort roomPort;
    private final RoomTypePort roomTypePort;

    @Override
    @Transactional
    public void handle(DeleteRoomUseCase message) {
        Room room = roomPort.findById(message.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        decreaseNumberOfRoomsOfRoomType(room.getRoomTypeId());

        roomPort.deleteById(room.getId());
    }

    private void decreaseNumberOfRoomsOfRoomType(long roomTypeId) {
        RoomType roomType = roomTypePort.findById(roomTypeId).orElseThrow();

        roomType.decreaseNumberOfRooms();

        roomTypePort.update(roomType);
    }

}
