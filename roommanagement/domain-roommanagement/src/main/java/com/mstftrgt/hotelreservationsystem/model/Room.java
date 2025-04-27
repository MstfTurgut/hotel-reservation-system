package com.mstftrgt.hotelreservationsystem.model;

import com.mstftrgt.hotelreservationsystem.dto.RoomCreate;
import com.mstftrgt.hotelreservationsystem.event.RoomAddedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RoomRemovedDomainEvent;
import com.mstftrgt.hotelreservationsystem.generic.domain.AggregateRoot;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Room extends AggregateRoot {

    private UUID id;
    private UUID roomTypeId;
    private String roomNumber;

    public static Room create(RoomCreate roomCreate) {
        Room newRoom = Room.builder()
                .id(UUID.randomUUID())
                .roomTypeId(roomCreate.roomTypeId())
                .roomNumber(roomCreate.roomNumber())
                .build();

        newRoom.registerEvent(new RoomAddedDomainEvent(roomCreate.roomTypeId()));

        return newRoom;
    }

    public void remove() {
        this.registerEvent(new RoomRemovedDomainEvent(roomTypeId));
    }
}
