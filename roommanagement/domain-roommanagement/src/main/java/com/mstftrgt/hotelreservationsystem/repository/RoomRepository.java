package com.mstftrgt.hotelreservationsystem.repository;

import com.mstftrgt.hotelreservationsystem.model.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository {


    Optional<Room> findById(UUID roomId);
    void save(Room room);

    List<Room> getAllRoomsOfRoomType(UUID roomTypeId);

    void remove(Room room);

    void removeAllByRoomTypeId(UUID roomTypeId);
}
