package com.mstftrgt.hotelreservationsystem.command.room.addnew;


import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.DomainEventPublisher;
import com.mstftrgt.hotelreservationsystem.event.RoomAdded;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddNewRoomCommandHandler implements CommandHandler<AddNewRoomCommand> {

    private final RoomRepository roomRepository;
    private final DomainEventPublisher eventPublisher;


    @Override
    public void handle(AddNewRoomCommand command) {
        roomRepository.save(command.getRoomNumber(), command.getRoomTypeId());

        eventPublisher.publish(new RoomAdded(command.getRoomTypeId()));
    }
}
