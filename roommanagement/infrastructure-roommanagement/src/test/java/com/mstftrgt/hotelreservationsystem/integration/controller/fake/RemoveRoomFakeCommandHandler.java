package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.command.room.remove.RemoveRoomCommand;
import com.mstftrgt.hotelreservationsystem.exception.RoomNotFoundException;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
import org.springframework.stereotype.Service;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.ROOM_NOT_FOUND_ID;

@Service
public class RemoveRoomFakeCommandHandler implements VoidCommandHandler<RemoveRoomCommand> {

    @Override
    public void handle(RemoveRoomCommand command) {
        if (ROOM_NOT_FOUND_ID.equals(command.roomId())) {
            throw new RoomNotFoundException(command.roomId());
        }
    }
}
