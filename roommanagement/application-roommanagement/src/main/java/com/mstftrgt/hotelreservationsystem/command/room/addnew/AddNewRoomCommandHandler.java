package com.mstftrgt.hotelreservationsystem.command.room.addnew;


import com.mstftrgt.hotelreservationsystem.cqrs.CommandHandler;
import com.mstftrgt.hotelreservationsystem.dto.RoomCreate;
import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddNewRoomCommandHandler implements CommandHandler<AddNewRoomCommand> {

    private final RoomRepository roomRepository;

    public void handle(AddNewRoomCommand addNewRoomCommand) {
        Room room = Room.create(buildRoomCreate(addNewRoomCommand));

        roomRepository.save(room);
    }

    private RoomCreate buildRoomCreate(AddNewRoomCommand command) {
        return RoomCreate.builder()
                .roomNumber(command.roomNumber())
                .roomTypeId(command.roomTypeId()).build();
    }

}
