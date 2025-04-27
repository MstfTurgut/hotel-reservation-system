package com.mstftrgt.hotelreservationsystem.command.room.addnew;


import com.mstftrgt.hotelreservationsystem.dto.RoomCreate;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandHandler;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddNewRoomCommandHandler implements CommandHandler<AddNewRoomCommand, UUID> {

    private final RoomRepository roomRepository;

    public UUID handle(AddNewRoomCommand addNewRoomCommand) {
        Room room = Room.create(buildRoomCreate(addNewRoomCommand));

        roomRepository.save(room);

        return room.getId();
    }

    private RoomCreate buildRoomCreate(AddNewRoomCommand command) {
        return RoomCreate.builder()
                .roomNumber(command.roomNumber())
                .roomTypeId(command.roomTypeId()).build();
    }

}
