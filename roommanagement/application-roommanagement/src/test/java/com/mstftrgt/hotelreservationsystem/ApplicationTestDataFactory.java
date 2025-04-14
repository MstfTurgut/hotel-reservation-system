package com.mstftrgt.hotelreservationsystem;


import com.mstftrgt.hotelreservationsystem.command.room.addnew.AddNewRoomCommand;
import com.mstftrgt.hotelreservationsystem.command.room.remove.RemoveRoomCommand;
import com.mstftrgt.hotelreservationsystem.command.roomtype.addnew.AddNewRoomTypeCommand;
import com.mstftrgt.hotelreservationsystem.command.roomtype.modify.ModifyRoomTypeCommand;
import com.mstftrgt.hotelreservationsystem.command.roomtype.remove.RemoveRoomTypeCommand;
import com.mstftrgt.hotelreservationsystem.event.RoomAddedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RoomRemovedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RoomTypeRemovedDomainEvent;
import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.query.room.findallforroomtype.FindAllRoomsOfRoomTypeQuery;
import com.mstftrgt.hotelreservationsystem.query.roomtype.findall.FindAllRoomTypesQuery;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ApplicationTestDataFactory {

    public static AddNewRoomCommand getAddNewRoomTestCommand() {
        return AddNewRoomCommand.builder()
                .roomNumber("101")
                .roomTypeId(UUID.randomUUID())
                .build();
    }

    public static RemoveRoomCommand getRemoveRoomTestCommand() {
        return RemoveRoomCommand.builder()
                .roomId(UUID.randomUUID())
                .build();
    }

    public static AddNewRoomTypeCommand getAddNewRoomTypeTestCommand() {
        return AddNewRoomTypeCommand.builder()
                .title("testTitle")
                .description("testDescription")
                .priceForNight(BigDecimal.TEN)
                .adultCapacity(2)
                .childCapacity(1)
                .build();
    }

    public static RemoveRoomTypeCommand getRemoveRoomTypeTestCommand() {
        return RemoveRoomTypeCommand.builder()
                .roomTypeId(UUID.randomUUID())
                .build();
    }

    public static ModifyRoomTypeCommand getModifyRoomTypeTestCommand() {
        return ModifyRoomTypeCommand.builder()
                .roomTypeId(UUID.randomUUID())
                .title("testTitle")
                .description("testDescription")
                .priceForNight(BigDecimal.TEN)
                .adultCapacity(2)
                .childCapacity(1)
                .build();
    }

    public static FindAllRoomsOfRoomTypeQuery getFindAllRoomsOfRoomTypeTestQuery() {
        return FindAllRoomsOfRoomTypeQuery.builder()
                .roomTypeId(UUID.randomUUID())
                .build();
    }

    public static FindAllRoomTypesQuery getFindAllRoomTypesTestQuery() {
        return FindAllRoomTypesQuery.builder().build();
    }

    public static List<Room> getTestRoomList() {
        return List.of(
                Room.builder()
                        .id(UUID.randomUUID())
                        .roomNumber("101")
                        .roomTypeId(UUID.randomUUID())
                        .build(),
                Room.builder()
                        .id(UUID.randomUUID())
                        .roomNumber("102")
                        .roomTypeId(UUID.randomUUID())
                        .build()
        );
    }

    public static List<RoomType> getTestRoomTypeList() {
        return List.of(
                RoomType.builder()
                        .id(UUID.randomUUID())
                        .title("testTitle")
                        .description("testDescription")
                        .priceForNight(BigDecimal.TEN)
                        .numberOfRooms(0)
                        .adultCapacity(2)
                        .childCapacity(1)
                        .build(),
                RoomType.builder()
                        .id(UUID.randomUUID())
                        .title("testTitle")
                        .description("testDescription")
                        .priceForNight(BigDecimal.TEN)
                        .numberOfRooms(0)
                        .adultCapacity(2)
                        .childCapacity(1)
                        .build()
        );
    }

    public static RoomAddedDomainEvent getRoomAddedTestDomainEvent() {
        return new RoomAddedDomainEvent(UUID.randomUUID());
    }

    public static RoomRemovedDomainEvent getRoomRemovedTestDomainEvent() {
        return new RoomRemovedDomainEvent(UUID.randomUUID());
    }

    public static RoomTypeRemovedDomainEvent getRoomTypeRemovedTestDomainEvent() {
        return new RoomTypeRemovedDomainEvent(UUID.randomUUID());
    }
}
