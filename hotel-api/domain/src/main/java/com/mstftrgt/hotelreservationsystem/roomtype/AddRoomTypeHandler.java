package com.mstftrgt.hotelreservationsystem.roomtype;

import com.mstftrgt.hotelreservationsystem.roomtype.message.usecase.AddRoomTypeUseCase;
import com.mstftrgt.hotelreservationsystem.roomtype.model.RoomType;
import com.mstftrgt.hotelreservationsystem.roomtype.port.RoomTypePort;
import io.craftgate.modulith.messaging.api.annotation.MessageHandlerConfig;
import io.craftgate.modulith.messaging.api.handler.MessageHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@MessageHandlerConfig(selector = AddRoomTypeUseCase.class)
public class AddRoomTypeHandler extends MessageHandler<AddRoomTypeUseCase, RoomType> {

    private final RoomTypePort roomTypePort;

    @Override
    public RoomType handle(AddRoomTypeUseCase message) {

        RoomType roomType = RoomType.create(message);
        return roomTypePort.save(roomType);
    }
}
