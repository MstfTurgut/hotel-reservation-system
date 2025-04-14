package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.DomainEventHandler;
import com.mstftrgt.hotelreservationsystem.event.RoomTypeRemovedDomainEvent;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomTypeRemovedDomainEventHandler extends DomainEventHandler<RoomTypeRemovedDomainEvent> {

    private final RoomRepository roomRepository;


    @Override
    public void handleEvent(RoomTypeRemovedDomainEvent event) {
        roomRepository.removeAllByRoomTypeId(event.roomTypeId());
    }
}
