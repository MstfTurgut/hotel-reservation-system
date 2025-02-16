package com.mstftrgt.hotelreservationsystem;


import java.math.BigDecimal;

import com.mstftrgt.hotelreservationsystem.roomtype.model.RoomType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mstftrgt.hotelreservationsystem.adapters.RoomTypeFakeAdapter;
import com.mstftrgt.hotelreservationsystem.roomtype.AddRoomTypeHandler;
import com.mstftrgt.hotelreservationsystem.roomtype.message.usecase.AddRoomTypeUseCase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AddRoomTypeTest {

    AddRoomTypeHandler addRoomTypeHandler;

    @BeforeEach
    void setUp() {
        addRoomTypeHandler = new AddRoomTypeHandler(new RoomTypeFakeAdapter());
    }

    @Test
    void should_add_room_type() {

      AddRoomTypeUseCase useCase = AddRoomTypeUseCase.builder()
              .title("Room Type 1")
              .description("Room Type 1 Description")
              .priceForNight(BigDecimal.valueOf(100))
              .adultCapacity(2)
              .childCapacity(1)
              .build();

        RoomType roomType = addRoomTypeHandler.handle(useCase);

        assertThat(roomType).isNotNull()
                .returns(1L, RoomType::getId)
                .returns("Room Type 1", RoomType::getTitle)
                .returns("Room Type 1 Description", RoomType::getDescription)
                .returns(BigDecimal.valueOf(100), RoomType::getPriceForNight)
                .returns(0, RoomType::getNumberOfRooms)
                .returns(2, RoomType::getAdultCapacity)
                .returns(1, RoomType::getChildCapacity);
    }

}