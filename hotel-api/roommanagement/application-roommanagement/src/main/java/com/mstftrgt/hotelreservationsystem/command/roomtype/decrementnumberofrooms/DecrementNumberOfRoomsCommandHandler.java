package com.mstftrgt.hotelreservationsystem.command.roomtype.decrementnumberofrooms;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DecrementNumberOfRoomsCommandHandler implements CommandHandler<DecrementNumberOfRoomsCommand> {

    RoomTypeRepository roomTypeRepository;

    @Override
    public void handle(DecrementNumberOfRoomsCommand command) {

        RoomType roomType = roomTypeRepository.findById(command.getRoomTypeId()).orElseThrow(
                () -> new IllegalArgumentException("com.mstftrgt.hotelreservationsystem.model.Room type not found"));

        roomType.decrementNumberOfRooms();

        roomTypeRepository.update(roomType);
    }
}
