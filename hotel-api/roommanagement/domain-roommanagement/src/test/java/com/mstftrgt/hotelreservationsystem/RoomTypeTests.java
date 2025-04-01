package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.dto.RoomTypeCreate;
import com.mstftrgt.hotelreservationsystem.dto.RoomTypeModify;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomTypeTests {


    @Test
    void create_ShouldCreateRoom() {
        RoomTypeCreate roomTypeCreate = RoomTypeTestDataFactory.getTestRoomTypeCreate();

        RoomType roomType = RoomType.create(roomTypeCreate);

        assertNotNull(roomType.getId());
        assertEquals(roomTypeCreate.title(), roomType.getTitle());
        assertEquals(roomTypeCreate.description(), roomType.getDescription());
        assertEquals(roomTypeCreate.priceForNight(), roomType.getPriceForNight());
        assertEquals(0, roomType.getNumberOfRooms());
        assertEquals(roomTypeCreate.adultCapacity(), roomType.getAdultCapacity());
        assertEquals(roomTypeCreate.childCapacity(), roomType.getChildCapacity());
    }

    @Test
    void modify_ShouldModifyRoom() {
        RoomType roomType = RoomTypeTestDataFactory.getTestRoomType();

        RoomTypeModify roomTypeModify = RoomTypeTestDataFactory.getTestRoomTypeModify();

        roomType.modify(roomTypeModify);

        assertEquals(roomTypeModify.title(), roomType.getTitle());
        assertEquals(roomTypeModify.description(), roomType.getDescription());
        assertEquals(roomTypeModify.priceForNight(), roomType.getPriceForNight());
        assertEquals(roomTypeModify.adultCapacity(), roomType.getAdultCapacity());
        assertEquals(roomTypeModify.childCapacity(), roomType.getChildCapacity());
    }

    @Test
    void incrementNumberOfRooms_ShouldIncrementNumberOfRooms() {
        RoomType roomType = RoomTypeTestDataFactory.getTestRoomType();

        int numberOfRooms = roomType.getNumberOfRooms();

        roomType.incrementNumberOfRooms();

        assertEquals(numberOfRooms + 1, roomType.getNumberOfRooms());
    }

    @Test
    void decrementNumberOfRooms_ShouldDecrementNumberOfRooms() {
        RoomType roomType = RoomTypeTestDataFactory.getTestRoomType();

        int numberOfRooms = roomType.getNumberOfRooms();

        roomType.decrementNumberOfRooms();

        assertEquals(numberOfRooms - 1, roomType.getNumberOfRooms());
    }

    @Test
    void canFit_ShouldReturnTrue_WhenGuestsAreWithinCapacity() {
        RoomType roomType = RoomTypeTestDataFactory.getTestRoomTypeWithCapacity(2, 2);

        boolean canFit = roomType.canFit(1, 1);

        assertTrue(canFit);
    }

    @Test
    void canFit_ShouldReturnTrue_WhenGuestsMatchExactCapacity() {
        RoomType roomType = RoomTypeTestDataFactory.getTestRoomTypeWithCapacity(2, 1);

        boolean canFit = roomType.canFit(2, 1);

        assertTrue(canFit);
    }

    @Test
    void canFit_ShouldReturnFalse_WhenAdultGuestsExceedCapacity() {
        RoomType roomType = RoomTypeTestDataFactory.getTestRoomTypeWithCapacity(2, 2);

        boolean canFit = roomType.canFit(3, 1);

        assertFalse(canFit);
    }

    @Test
    void canFit_ShouldReturnFalse_WhenChildGuestsExceedCapacity() {
        RoomType roomType = RoomTypeTestDataFactory.getTestRoomTypeWithCapacity(2, 1);

        boolean canFit = roomType.canFit(2, 2);

        assertFalse(canFit);
    }

    @Test
    void canFit_ShouldReturnFalse_WhenBothAdultAndChildGuestsExceedCapacity() {
        RoomType roomType = RoomTypeTestDataFactory.getTestRoomTypeWithCapacity(1, 1);

        boolean canFit = roomType.canFit(2, 2);

        assertFalse(canFit);
    }

    @Test
    void canFit_ShouldReturnTrue_WhenZeroGuests() {
        RoomType roomType = RoomTypeTestDataFactory.getTestRoomTypeWithCapacity(1, 1);

        boolean canFit = roomType.canFit(0, 0);

        assertTrue(canFit);
    }
}
