package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.DomainEventHandler;
import com.mstftrgt.hotelreservationsystem.event.RoomAddedDomainEvent;
import com.mstftrgt.hotelreservationsystem.exception.RoomTypeNotFoundException;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomAddedDomainEventHandler implements DomainEventHandler<RoomAddedDomainEvent> {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public void handle(RoomAddedDomainEvent event) {
        RoomType roomType = roomTypeRepository.findById(event.roomTypeId())
                .orElseThrow(() -> new RoomTypeNotFoundException(event.roomTypeId()));

        roomType.incrementNumberOfRooms();

        roomTypeRepository.save(roomType);
    }
}
