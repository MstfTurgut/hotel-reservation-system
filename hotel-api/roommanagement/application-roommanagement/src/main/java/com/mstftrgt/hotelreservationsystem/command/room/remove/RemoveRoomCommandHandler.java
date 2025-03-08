package com.mstftrgt.hotelreservationsystem.command.room.remove;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.DomainEventPublisher;
import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.event.RoomRemoved;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveRoomCommandHandler implements CommandHandler<RemoveRoomCommand> {

    private final RoomRepository roomRepository;
    private final DomainEventPublisher eventPublisher;

    @Override
    public void handle(RemoveRoomCommand command) {
        Room room = roomRepository.findById(command.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("com.mstftrgt.hotelreservationsystem.model.Room not found"));

        roomRepository.deleteById(command.getRoomId());

        eventPublisher.publish(new RoomRemoved(room.getRoomTypeId()));
    }
}
