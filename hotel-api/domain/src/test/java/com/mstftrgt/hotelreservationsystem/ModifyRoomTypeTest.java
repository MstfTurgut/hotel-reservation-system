package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.adapters.RoomTypeFakeAdapter;
import com.mstftrgt.hotelreservationsystem.roomtype.ModifyRoomTypeHandler;
import com.mstftrgt.hotelreservationsystem.roomtype.message.usecase.ModifyRoomTypeUseCase;
import com.mstftrgt.hotelreservationsystem.roomtype.model.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ModifyRoomTypeTest {

    ModifyRoomTypeHandler modifyRoomTypeHandler;

    @BeforeEach
    void setUp() {
        modifyRoomTypeHandler = new ModifyRoomTypeHandler(new RoomTypeFakeAdapter());
    }


    @Test
    void should_modify_room_type() {

        ModifyRoomTypeUseCase useCase = ModifyRoomTypeUseCase.builder()
                .id(1L)
                .title("Room Type 1")
                .description("Room Type 1 Description")
                .priceForNight(BigDecimal.valueOf(100))
                .adultCapacity(2)
                .childCapacity(1)
                .build();

        RoomType roomType = modifyRoomTypeHandler.handle(useCase);

        assertThat(roomType).isNotNull()
                .returns(1L, RoomType::getId)
                .returns("Room Type 1", RoomType::getTitle)
                .returns("Room Type 1 Description", RoomType::getDescription)
                .returns(BigDecimal.valueOf(100), RoomType::getPriceForNight)
                .returns(2, RoomType::getAdultCapacity)
                .returns(1, RoomType::getChildCapacity);
    }


    @Test
    void should_not_modify_room_type_if_room_type_does_not_exist() {

        ModifyRoomTypeUseCase useCase = ModifyRoomTypeUseCase.builder()
                .id(9999L)
                .title("Room Type 1")
                .description("Room Type 1 Description")
                .priceForNight(BigDecimal.valueOf(100))
                .adultCapacity(2)
                .childCapacity(1)
                .build();


        assertThatThrownBy(() -> modifyRoomTypeHandler.handle(useCase))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("Room type not found");
    }
}
