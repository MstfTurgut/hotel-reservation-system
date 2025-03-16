package com.mstftrgt.hotelreservationsystem.command.roomtype.addnew;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.dto.RoomTypeCreate;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddNewRoomTypeCommandHandler implements CommandHandler<AddNewRoomTypeCommand> {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public void handle(AddNewRoomTypeCommand command) {
        RoomType roomType = RoomType.create(command.toRoomTypeCreate());

        roomTypeRepository.save(roomType);
    }
}
