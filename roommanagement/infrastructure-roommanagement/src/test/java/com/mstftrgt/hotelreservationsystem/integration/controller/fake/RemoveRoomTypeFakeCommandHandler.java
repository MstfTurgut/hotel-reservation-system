package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.command.roomtype.remove.RemoveRoomTypeCommand;
import com.mstftrgt.hotelreservationsystem.exception.RoomTypeNotFoundException;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
import org.springframework.stereotype.Service;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.ROOM_TYPE_NOT_FOUND_ID;

@Service
public class RemoveRoomTypeFakeCommandHandler implements VoidCommandHandler<RemoveRoomTypeCommand> {

    @Override
    public void handle(RemoveRoomTypeCommand command) {
        if(ROOM_TYPE_NOT_FOUND_ID.equals(command.roomTypeId())) {
            throw new RoomTypeNotFoundException(command.roomTypeId());
        }
    }
}
