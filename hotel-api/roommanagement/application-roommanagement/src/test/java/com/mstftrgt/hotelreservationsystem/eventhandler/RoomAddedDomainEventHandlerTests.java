package com.mstftrgt.hotelreservationsystem.eventhandler;


import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.event.RoomAddedDomainEvent;
import com.mstftrgt.hotelreservationsystem.eventhandler.RoomAddedDomainEventHandler;
import com.mstftrgt.hotelreservationsystem.exception.RoomTypeNotFoundException;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomAddedDomainEventHandlerTests {
    @Mock
    RoomType roomType;

    @Mock
    RoomTypeRepository roomTypeRepository;

    @InjectMocks
    RoomAddedDomainEventHandler handler;

    @Test
    void shouldHandleRoomAddedDomainEventSuccessfully() {
        RoomAddedDomainEvent event = ApplicationTestDataFactory.getRoomAddedDomainEvent();

        when(roomTypeRepository.findById(event.roomTypeId())).thenReturn(Optional.of(roomType));

        handler.handle(event);

        verify(roomType).incrementNumberOfRooms();
        verify(roomTypeRepository).save(roomType);
    }

    @Test
    void shouldThrowRoomTypeNotFoundExceptionWhenRoomTypeNotFound() {
        RoomAddedDomainEvent event = ApplicationTestDataFactory.getRoomAddedDomainEvent();

        when(roomTypeRepository.findById(event.roomTypeId())).thenReturn(Optional.empty());

        assertThrows(RoomTypeNotFoundException.class, () -> handler.handle(event));

        verifyNoInteractions(roomType);
        verify(roomTypeRepository, never()).save(any());
    }

}
