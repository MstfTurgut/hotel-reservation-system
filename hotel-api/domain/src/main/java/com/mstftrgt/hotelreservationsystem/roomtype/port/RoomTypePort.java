package com.mstftrgt.hotelreservationsystem.roomtype.port;

import java.util.List;
import java.util.Optional;

import com.mstftrgt.hotelreservationsystem.roomtype.model.RoomType;

public interface RoomTypePort {

    Optional<RoomType> findById(long roomTypeId);
    List<RoomType> findAll();

    RoomType save(RoomType roomType);

    RoomType update(RoomType roomType);
}
