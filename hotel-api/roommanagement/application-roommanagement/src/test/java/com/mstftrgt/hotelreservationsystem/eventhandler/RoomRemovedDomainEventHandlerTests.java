package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.event.RoomRemovedDomainEvent;
import com.mstftrgt.hotelreservationsystem.eventhandler.RoomRemovedDomainEventHandler;
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
public class RoomRemovedDomainEventHandlerTests {
    @Mock
    RoomType roomType;

    @Mock
    RoomTypeRepository roomTypeRepository;

    @InjectMocks
    RoomRemovedDomainEventHandler handler;

    @Test
    void shouldHandleRoomRemovedDomainEventSuccessfully() {
        RoomRemovedDomainEvent event = ApplicationTestDataFactory.getRoomRemovedDomainEvent();

        when(roomTypeRepository.findById(event.roomTypeId())).thenReturn(Optional.of(roomType));

        handler.handle(event);

        verify(roomType).decrementNumberOfRooms();
        verify(roomTypeRepository).save(roomType);
    }

    @Test
    void shouldThrowRoomTypeNotFoundExceptionWhenRoomTypeNotFound() {
        RoomRemovedDomainEvent event = ApplicationTestDataFactory.getRoomRemovedDomainEvent();

        when(roomTypeRepository.findById(event.roomTypeId())).thenReturn(Optional.empty());

        assertThrows(RoomTypeNotFoundException.class, () -> handler.handle(event));

        verifyNoInteractions(roomType);
        verify(roomTypeRepository, never()).save(any());
    }

}
