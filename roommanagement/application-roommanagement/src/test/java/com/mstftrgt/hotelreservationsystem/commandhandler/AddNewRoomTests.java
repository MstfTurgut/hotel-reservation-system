package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.room.addnew.AddNewRoomCommand;
import com.mstftrgt.hotelreservationsystem.command.room.addnew.AddNewRoomCommandHandler;
import com.mstftrgt.hotelreservationsystem.dto.RoomCreate;
import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
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
public class AddNewRoomTests {

    @Mock
    Room room;

    @Mock
    RoomRepository roomRepository;

    @InjectMocks
    AddNewRoomCommandHandler handler;

    @Test
    void shouldAddNewRoomSuccessfully() {
        AddNewRoomCommand command = ApplicationTestDataFactory.getAddNewRoomTestCommand();

        try (MockedStatic<Room> roomStatic = mockStatic(Room.class)) {
            roomStatic.when(() -> Room.create(any(RoomCreate.class)))
                    .thenReturn(room);

            handler.handle(command);
        }


        verify(roomRepository).save(room);
    }
}
