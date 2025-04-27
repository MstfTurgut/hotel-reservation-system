package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.event.RoomRemovedDomainEvent;
import com.mstftrgt.hotelreservationsystem.exception.RoomTypeNotFoundException;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomRemovedDomainEventHandler {

    private final RoomTypeRepository roomTypeRepository;

    @EventListener
    public void handleEvent(RoomRemovedDomainEvent event) {
        log.info("Handling domain event: {}", event);

        RoomType roomType = roomTypeRepository.findById(event.roomTypeId())
                .orElseThrow(() -> new RoomTypeNotFoundException(event.roomTypeId()));

        roomType.decrementNumberOfRooms();

        roomTypeRepository.save(roomType);
    }
}
