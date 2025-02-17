package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.adapters.RoomFakeAdapter;
import com.mstftrgt.hotelreservationsystem.adapters.RoomTypeFakeAdapter;
import com.mstftrgt.hotelreservationsystem.room.AddRoomHandler;
import com.mstftrgt.hotelreservationsystem.room.message.usecase.AddRoomUseCase;
import com.mstftrgt.hotelreservationsystem.room.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class AddRoomTest {

    RoomTypeFakeAdapter roomTypeFakeAdapter;

    RoomFakeAdapter roomFakeAdapter;
    AddRoomHandler addRoomHandler;

    @BeforeEach
    void setUp() {
        roomTypeFakeAdapter = new RoomTypeFakeAdapter();
        roomFakeAdapter = new RoomFakeAdapter();
        addRoomHandler = new AddRoomHandler(roomFakeAdapter, roomTypeFakeAdapter);
    }


    @Test
    void should_add_room() {
        AddRoomUseCase useCase = AddRoomUseCase.builder()
                .roomTypeId(1L)
                .roomNumber("101")
                .build();

        Room room = addRoomHandler.handle(useCase);

        assertThat(room).isNotNull()
                .returns(1L, Room::getId)
                .returns(1L, Room::getRoomTypeId)
                .returns("101", Room::getRoomNumber);

        assertThat(roomTypeFakeAdapter.isCalled()).isEqualTo(true);
        assertThat(roomFakeAdapter.isCalled()).isEqualTo(true);
    }

    @Test
    void should_fail_if_room_type_does_not_exist() {
        AddRoomUseCase useCase = AddRoomUseCase.builder()
                .roomTypeId(9999L)
                .roomNumber("101")
                .build();

        assertThatThrownBy(() -> addRoomHandler.handle(useCase))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Room type not found");

        assertThat(roomTypeFakeAdapter.isCalled()).isEqualTo(true);
        assertThat(roomFakeAdapter.isCalled()).isEqualTo(false);
    }

}
