package com.mstftrgt.hotelreservationsystem.queryhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.query.room.findallforroomtype.FindAllRoomsOfRoomTypeQuery;
import com.mstftrgt.hotelreservationsystem.query.room.findallforroomtype.FindAllRoomsOfRoomTypeQueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomReadModel;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllRoomsOfRoomTypeTests {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private FindAllRoomsOfRoomTypeQueryHandler handler;

    @Test
    void shouldReturnReservationReadModelsWhenReservationsExist() {
        FindAllRoomsOfRoomTypeQuery query = ApplicationTestDataFactory.getFindAllRoomsOfRoomTypeTestQuery();

        List<Room> testRoomList = ApplicationTestDataFactory.getTestRoomList();

        List<RoomReadModel> expectedReadModels = testRoomList.stream()
                .map(RoomReadModel::from)
                .toList();

        when(roomRepository.getAllRoomsOfRoomType(query.roomTypeId()))
                .thenReturn(testRoomList);

        List<RoomReadModel> result = handler.handle(query);

        assertEquals(expectedReadModels, result);
    }

    @Test
    void shouldReturnEmptyListWhenNoReservationsExist() {
        FindAllRoomsOfRoomTypeQuery query = ApplicationTestDataFactory.getFindAllRoomsOfRoomTypeTestQuery();

        when(roomRepository.getAllRoomsOfRoomType(query.roomTypeId()))
                .thenReturn(Collections.emptyList());

        List<RoomReadModel> result = handler.handle(query);

        assertEquals(Collections.emptyList(), result);
    }
}