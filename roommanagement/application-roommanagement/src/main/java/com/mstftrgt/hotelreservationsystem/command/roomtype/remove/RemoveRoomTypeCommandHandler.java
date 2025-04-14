package com.mstftrgt.hotelreservationsystem.command.roomtype.remove;


import com.mstftrgt.hotelreservationsystem.cqrs.CommandHandler;
import com.mstftrgt.hotelreservationsystem.exception.RoomTypeNotFoundException;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveRoomTypeCommandHandler implements CommandHandler<RemoveRoomTypeCommand> {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public void handle(RemoveRoomTypeCommand command) {
        RoomType roomType = roomTypeRepository.findById(command.roomTypeId())
                .orElseThrow(() -> new RoomTypeNotFoundException(command.roomTypeId()));

        roomType.remove();

        roomTypeRepository.remove(roomType);
    }
}
