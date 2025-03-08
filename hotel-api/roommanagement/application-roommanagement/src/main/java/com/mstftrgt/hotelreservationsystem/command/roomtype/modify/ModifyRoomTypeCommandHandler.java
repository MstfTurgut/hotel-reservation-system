package com.mstftrgt.hotelreservationsystem.command.roomtype.modify;

import com.mstftrgt.hotelreservationsystem.CommandHandler;

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
        RoomType roomType = roomTypeRepository.findById(command.getId()).orElseThrow(
                () -> new IllegalArgumentException("com.mstftrgt.hotelreservationsystem.model.Room type not found"));

        roomType.modify(
                command.getTitle(),
                command.getDescription(),
                command.getPriceForNight(),
                command.getAdultCapacity(),
                command.getChildCapacity());

        roomTypeRepository.update(roomType);
    }
}
