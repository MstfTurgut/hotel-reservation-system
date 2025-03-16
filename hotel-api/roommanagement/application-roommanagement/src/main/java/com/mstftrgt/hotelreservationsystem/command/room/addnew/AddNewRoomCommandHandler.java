package com.mstftrgt.hotelreservationsystem.command.room.addnew;


import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddNewRoomCommandHandler implements CommandHandler<AddNewRoomCommand> {

    private final RoomRepository roomRepository;


    @Override
    public void handle(AddNewRoomCommand command) {
        Room room = Room.create(command.toRoomCreate());

        roomRepository.save(room);
    }
}
