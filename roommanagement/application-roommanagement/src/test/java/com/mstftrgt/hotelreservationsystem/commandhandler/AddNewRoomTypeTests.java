package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.roomtype.addnew.AddNewRoomTypeCommand;
import com.mstftrgt.hotelreservationsystem.command.roomtype.addnew.AddNewRoomTypeCommandHandler;
import com.mstftrgt.hotelreservationsystem.dto.RoomTypeCreate;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddNewRoomTypeTests {

    @Mock
    RoomType roomType;

    @Mock
    RoomTypeRepository roomTypeRepository;

    @InjectMocks
    AddNewRoomTypeCommandHandler handler;

    @Test
    void shouldAddNewRoomSuccessfully() {
        AddNewRoomTypeCommand command = ApplicationTestDataFactory.getAddNewRoomTypeTestCommand();

        try (MockedStatic<RoomType> roomTypeStatic = mockStatic(RoomType.class)) {
            roomTypeStatic.when(() -> RoomType.create(any(RoomTypeCreate.class)))
                    .thenReturn(roomType);

            handler.handle(command);
        }

        verify(roomTypeRepository).save(roomType);
    }
}
