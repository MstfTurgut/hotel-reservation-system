package com.mstftrgt.hotelreservationsystem.command.roomtype.modify;

import com.mstftrgt.hotelreservationsystem.dto.RoomTypeModify;
import com.mstftrgt.hotelreservationsystem.exception.RoomTypeNotFoundException;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandHandler;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyRoomTypeCommandHandler implements VoidCommandHandler<ModifyRoomTypeCommand> {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public void handle(ModifyRoomTypeCommand command) {
        RoomType roomType = roomTypeRepository.findById(command.roomTypeId())
                .orElseThrow(() -> new RoomTypeNotFoundException(command.roomTypeId()));

        roomType.modify(buildRoomTypeModify(command));

        roomTypeRepository.save(roomType);
    }

    private static RoomTypeModify buildRoomTypeModify(ModifyRoomTypeCommand command) {
        return RoomTypeModify.builder()
                .title(command.title())
                .description(command.description())
                .priceForNight(command.priceForNight())
                .adultCapacity(command.adultCapacity())
                .childCapacity(command.childCapacity())
                .build();
    }
}
