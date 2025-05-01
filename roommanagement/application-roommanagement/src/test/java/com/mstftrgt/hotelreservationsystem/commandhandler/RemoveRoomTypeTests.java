package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.roomtype.remove.RemoveRoomTypeCommand;
import com.mstftrgt.hotelreservationsystem.command.roomtype.remove.RemoveRoomTypeCommandHandler;
import com.mstftrgt.hotelreservationsystem.exception.RoomTypeNotFoundException;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemoveRoomTypeTests {

    @Mock
    RoomType roomType;

    @Mock
    RoomTypeRepository roomTypeRepository;

    @InjectMocks
    RemoveRoomTypeCommandHandler handler;

    @Test
    void shouldRemoveRoomSuccessfully() {
        RemoveRoomTypeCommand command = ApplicationTestDataFactory.getRemoveRoomTypeTestCommand();

        when(roomTypeRepository.findById(command.roomTypeId())).thenReturn(Optional.of(roomType));

        handler.handle(command);

        verify(roomTypeRepository).remove(roomType);
    }


    @Test
    void shouldThrowWhenRoomNotFound() {
        RemoveRoomTypeCommand command = ApplicationTestDataFactory.getRemoveRoomTypeTestCommand();

        when(roomTypeRepository.findById(command.roomTypeId())).thenReturn(Optional.empty());

        assertThrows(RoomTypeNotFoundException.class, () -> handler.handle(command));

        verify(roomTypeRepository, never()).remove(any());
    }

}
