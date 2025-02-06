package com.mstftrgt.hotelreservationsystem.roomtype.port;

import com.mstftrgt.hotelreservationsystem.roomtype.model.RoomType;

import java.util.List;

public interface RoomTypePort {

    RoomType findById(long roomTypeId);
    List<RoomType> findAll();
    void incrementNumberOfRooms(long roomTypeId);
    void decrementNumberOfRooms(long roomTypeId);
}
