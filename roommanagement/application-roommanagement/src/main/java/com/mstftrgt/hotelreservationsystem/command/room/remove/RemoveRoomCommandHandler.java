package com.mstftrgt.hotelreservationsystem.command.room.remove;

import com.mstftrgt.hotelreservationsystem.exception.RoomNotFoundException;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandHandler;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveRoomCommandHandler implements VoidCommandHandler<RemoveRoomCommand> {

    private final RoomRepository roomRepository;

    @Override
    public void handle(RemoveRoomCommand command) {
        Room room = roomRepository.findById(command.roomId())
                .orElseThrow(() -> new RoomNotFoundException(command.roomId()));

        room.remove();

        roomRepository.remove(room);
    }
}
