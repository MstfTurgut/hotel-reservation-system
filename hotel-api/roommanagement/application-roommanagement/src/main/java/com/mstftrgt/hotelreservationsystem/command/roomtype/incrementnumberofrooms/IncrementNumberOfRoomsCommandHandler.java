package com.mstftrgt.hotelreservationsystem.command.roomtype.incrementnumberofrooms;

import com.mstftrgt.hotelreservationsystem.CommandHandler;

import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncrementNumberOfRoomsCommandHandler implements CommandHandler<IncrementNumberOfRoomsCommand> {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public void handle(IncrementNumberOfRoomsCommand command) {

        RoomType roomType = roomTypeRepository.findById(command.getRoomTypeId()).orElseThrow(
                () -> new IllegalArgumentException("com.mstftrgt.hotelreservationsystem.model.Room type not found"));

        roomType.incrementNumberOfRooms();

        roomTypeRepository.update(roomType);
    }
}
