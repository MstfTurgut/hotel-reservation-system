package com.mstftrgt.hotelreservationsystem.repository;

import com.mstftrgt.hotelreservationsystem.domain.Repository;
import com.mstftrgt.hotelreservationsystem.model.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends Repository {


    Optional<Room> findById(UUID roomId);
    void save(Room room);

    List<Room> getAllRoomsOfRoomType(UUID roomTypeId);

    void deleteById(UUID roomId);
}
