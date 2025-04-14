package com.mstftrgt.hotelreservationsystem.repository;

import com.mstftrgt.hotelreservationsystem.model.RoomType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomTypeRepository {

    Optional<RoomType> findById(UUID roomTypeId);

    RoomType findByRoomId(UUID roomId);

    void save(RoomType roomType);

    List<RoomType> findAll();

    void remove(RoomType roomType);
}
