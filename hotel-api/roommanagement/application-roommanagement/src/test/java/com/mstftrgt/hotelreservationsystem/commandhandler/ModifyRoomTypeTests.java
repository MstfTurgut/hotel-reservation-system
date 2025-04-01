package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.roomtype.modify.ModifyRoomTypeCommand;
import com.mstftrgt.hotelreservationsystem.command.roomtype.modify.ModifyRoomTypeCommandHandler;
import com.mstftrgt.hotelreservationsystem.dto.RoomTypeModify;
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
public class ModifyRoomTypeTests {

    @Mock
    RoomType roomType;

    @Mock
    RoomTypeRepository roomTypeRepository;

    @InjectMocks
    ModifyRoomTypeCommandHandler handler;

    @Test
    void shouldModifyRoomTypeSuccessfully() {
        ModifyRoomTypeCommand command = ApplicationTestDataFactory.getModifyRoomTypeTestCommand();

        when(roomTypeRepository.findById(command.roomTypeId())).thenReturn(Optional.of(roomType));

        handler.handle(command);

        verify(roomType).modify(any(RoomTypeModify.class));
        verify(roomTypeRepository).save(roomType);
    }

    @Test
    void shouldThrowWhenRoomTypeNotFound() {
        ModifyRoomTypeCommand command = ApplicationTestDataFactory.getModifyRoomTypeTestCommand();

        when(roomTypeRepository.findById(command.roomTypeId())).thenReturn(Optional.empty());

        assertThrows(RoomTypeNotFoundException.class, () -> handler.handle(command));

        verify(roomType, never()).modify(any());
        verify(roomTypeRepository, never()).save(any());
    }

}
