package com.mstftrgt.hotelreservationsystem.command.roomtype.modify;

import com.mstftrgt.hotelreservationsystem.CommandHandler;

import com.mstftrgt.hotelreservationsystem.exception.RoomTypeNotFoundException;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyRoomTypeCommandHandler implements CommandHandler<ModifyRoomTypeCommand> {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public void handle(ModifyRoomTypeCommand command) {
        RoomType roomType = roomTypeRepository.findById(command.roomTypeId())
                .orElseThrow(() -> new RoomTypeNotFoundException(command.roomTypeId()));

        roomType.modify(command.toRoomTypeModify());

        roomTypeRepository.save(roomType);
    }
}
