package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.event.RoomTypeRemovedDomainEvent;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RoomTypeRemovedDomainEventHandlerTests {

    @Mock
    RoomRepository roomRepository;

    @InjectMocks
    RoomTypeRemovedDomainEventHandler handler;

    @Test
    void shouldHandleRoomTypeRemovedDomainEventSuccessfully() {
        RoomTypeRemovedDomainEvent event = ApplicationTestDataFactory.getRoomTypeRemovedTestDomainEvent();

        handler.handleEvent(event);

        verify(roomRepository).removeAllByRoomTypeId(event.roomTypeId());
    }
}
