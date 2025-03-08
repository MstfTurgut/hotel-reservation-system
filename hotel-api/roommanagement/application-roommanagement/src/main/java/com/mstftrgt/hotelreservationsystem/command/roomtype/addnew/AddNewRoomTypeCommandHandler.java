package com.mstftrgt.hotelreservationsystem.command.roomtype.addnew;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.repository.dto.RoomTypeCreate;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddNewRoomTypeCommandHandler implements CommandHandler<AddNewRoomTypeCommand> {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public void handle(AddNewRoomTypeCommand command) {
        roomTypeRepository.save(buildRoomTypeCreate(command));
    }

    private static RoomTypeCreate buildRoomTypeCreate(AddNewRoomTypeCommand command) {
        return new RoomTypeCreate(
                command.getTitle(),
                command.getDescription(),
                command.getPriceForNight(),
                command.getAdultCapacity(),
                command.getChildCapacity()
        );
    }
}
