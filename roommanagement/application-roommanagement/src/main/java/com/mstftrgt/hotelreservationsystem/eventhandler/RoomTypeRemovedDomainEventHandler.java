package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.event.RoomTypeRemovedDomainEvent;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomTypeRemovedDomainEventHandler {

    private final RoomRepository roomRepository;


    @EventListener
    public void handleEvent(RoomTypeRemovedDomainEvent event) {
        log.info("Handling domain event: {}", event);

        roomRepository.removeAllByRoomTypeId(event.roomTypeId());
    }
}
