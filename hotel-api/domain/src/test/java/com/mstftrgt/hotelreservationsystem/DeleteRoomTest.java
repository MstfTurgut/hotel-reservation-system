package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.adapters.RoomFakeAdapter;
import com.mstftrgt.hotelreservationsystem.adapters.RoomTypeFakeAdapter;
import com.mstftrgt.hotelreservationsystem.room.DeleteRoomHandler;
import com.mstftrgt.hotelreservationsystem.room.message.usecase.DeleteRoomUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DeleteRoomTest {

    RoomFakeAdapter roomFakeAdapter;

    RoomTypeFakeAdapter roomTypeFakeAdapter;
    DeleteRoomHandler deleteRoomHandler;

    @BeforeEach
    void setUp() {
        roomFakeAdapter = new RoomFakeAdapter();
        roomTypeFakeAdapter = new RoomTypeFakeAdapter();
        deleteRoomHandler = new DeleteRoomHandler(roomFakeAdapter, roomTypeFakeAdapter);
    }

    @Test
    void should_delete_room() {
        DeleteRoomUseCase deleteRoomUseCase = DeleteRoomUseCase.builder()
                .roomId(1L)
                .build();

        deleteRoomHandler.handle(deleteRoomUseCase);

        assertThat(roomFakeAdapter.isCalled()).isEqualTo(true);
        assertThat(roomTypeFakeAdapter.isCalled()).isEqualTo(true);
    }

    @Test
    void should_not_delete_room_if_room_does_not_exist() {
        DeleteRoomUseCase deleteRoomUseCase = DeleteRoomUseCase.builder()
                .roomId(9999L)
                .build();

        assertThatThrownBy(() -> deleteRoomHandler.handle(deleteRoomUseCase))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Room not found");

        assertThat(roomFakeAdapter.isCalled()).isEqualTo(true);
        assertThat(roomTypeFakeAdapter.isCalled()).isEqualTo(false);
    }


}
