package com.mstftrgt.hotelreservationsystem.roomtype;


import com.mstftrgt.hotelreservationsystem.roomtype.message.usecase.ModifyRoomTypeUseCase;
import com.mstftrgt.hotelreservationsystem.roomtype.model.RoomType;
import com.mstftrgt.hotelreservationsystem.roomtype.port.RoomTypePort;
import io.craftgate.modulith.messaging.api.annotation.MessageHandlerConfig;
import io.craftgate.modulith.messaging.api.handler.MessageHandler;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@MessageHandlerConfig(selector = ModifyRoomTypeUseCase.class)
public class ModifyRoomTypeHandler extends MessageHandler<ModifyRoomTypeUseCase, RoomType> {

    private final RoomTypePort roomTypePort;

    @Override
    public RoomType handle(ModifyRoomTypeUseCase message) {
        RoomType roomType = roomTypePort
                .findById(message.getId()).orElseThrow(() -> new RuntimeException("Room type not found"));

        roomType.modify(message);

        return roomTypePort.update(roomType);
    }
}
