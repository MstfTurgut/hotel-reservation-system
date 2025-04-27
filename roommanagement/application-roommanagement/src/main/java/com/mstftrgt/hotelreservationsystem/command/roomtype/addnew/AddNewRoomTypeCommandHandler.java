package com.mstftrgt.hotelreservationsystem.command.roomtype.addnew;

import com.mstftrgt.hotelreservationsystem.dto.RoomTypeCreate;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandHandler;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddNewRoomTypeCommandHandler implements CommandHandler<AddNewRoomTypeCommand, UUID> {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public UUID handle(AddNewRoomTypeCommand command) {
        RoomType roomType = RoomType.create(buildRoomTypeCreate(command));

        roomTypeRepository.save(roomType);

        return roomType.getId();
    }

    private static RoomTypeCreate buildRoomTypeCreate(AddNewRoomTypeCommand command) {
        return RoomTypeCreate.builder()
                .title(command.title())
                .description(command.description())
                .priceForNight(command.priceForNight())
                .adultCapacity(command.adultCapacity())
                .childCapacity(command.childCapacity())
                .build();
    }
}
