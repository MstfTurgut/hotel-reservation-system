package com.mstftrgt.hotelreservationsystem.queryhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.query.roomtype.findall.FindAllRoomTypesQuery;
import com.mstftrgt.hotelreservationsystem.query.roomtype.findall.FindAllRoomTypesQueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomTypeReadModel;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
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
class FindAllRoomTypesTests {

    @Mock
    private RoomTypeRepository roomTypeRepository;

    @InjectMocks
    private FindAllRoomTypesQueryHandler handler;

    @Test
    void shouldReturnReservationReadModelsWhenReservationsExist() {
        FindAllRoomTypesQuery query = ApplicationTestDataFactory.getFindAllRoomTypesTestQuery();

        List<RoomType> testRoomTypeList = ApplicationTestDataFactory.getTestRoomTypeList();

        List<RoomTypeReadModel> expectedReadModels = testRoomTypeList.stream()
                .map(RoomTypeReadModel::from)
                .toList();

        when(roomTypeRepository.findAll())
                .thenReturn(testRoomTypeList);

        List<RoomTypeReadModel> result = handler.handle(query);

        assertEquals(expectedReadModels, result);
    }

    @Test
    void shouldReturnEmptyListWhenNoReservationsExist() {
        FindAllRoomTypesQuery query = ApplicationTestDataFactory.getFindAllRoomTypesTestQuery();

        when(roomTypeRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<RoomTypeReadModel> result = handler.handle(query);

        assertEquals(Collections.emptyList(), result);
    }
}