package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.room.remove.RemoveRoomCommand;
import com.mstftrgt.hotelreservationsystem.command.room.remove.RemoveRoomCommandHandler;
import com.mstftrgt.hotelreservationsystem.exception.RoomNotFoundException;
import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
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
public class RemoveRoomTests {

    @Mock
    Room room;

    @Mock
    RoomRepository roomRepository;

    @InjectMocks
    RemoveRoomCommandHandler handler;

    @Test
    void shouldRemoveRoomSuccessfully() {
        RemoveRoomCommand command = ApplicationTestDataFactory.getRemoveRoomTestCommand();

        when(roomRepository.findById(command.roomId())).thenReturn(Optional.of(room));

        handler.handle(command);

        verify(room).remove();
        verify(roomRepository).remove(room);
    }


    @Test
    void shouldThrowWhenRoomNotFound() {
        RemoveRoomCommand command = ApplicationTestDataFactory.getRemoveRoomTestCommand();

        when(roomRepository.findById(command.roomId())).thenReturn(Optional.empty());

        assertThrows(RoomNotFoundException.class, () -> handler.handle(command));

        verify(room, never()).remove();
        verify(roomRepository, never()).remove(any());
    }

}
