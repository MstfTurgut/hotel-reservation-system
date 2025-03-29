package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.dto.RoomCreate;
import com.mstftrgt.hotelreservationsystem.event.RoomAddedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RoomRemovedDomainEvent;
import com.mstftrgt.hotelreservationsystem.model.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoomTests {


    @Test
    void create_ShouldCreateRoomAndRegisterDomainEvent() {
        RoomCreate roomCreate = RoomTestDataFactory.getTestRoomCreate();

        Room room = Room.create(roomCreate);

        assertNotNull(room.getId());
        assertEquals(roomCreate.roomTypeId(), room.getRoomTypeId());
        assertEquals(roomCreate.roomNumber(), room.getRoomNumber());

        assertEquals(1, room.getDomainEvents().size());

        RoomAddedDomainEvent event = (RoomAddedDomainEvent) room.getDomainEvents().get(0);

        assertEquals(roomCreate.roomTypeId(), event.roomTypeId());
    }

    @Test
    void remove_ShouldRegisterRoomRemovedDomainEvent() {
        Room room = RoomTestDataFactory.getTestRoom();

        room.remove();

        assertEquals(1, room.getDomainEvents().size());

        RoomRemovedDomainEvent event = (RoomRemovedDomainEvent) room.getDomainEvents().get(0);

        assertEquals(room.getRoomTypeId(), event.roomTypeId());
    }
}
