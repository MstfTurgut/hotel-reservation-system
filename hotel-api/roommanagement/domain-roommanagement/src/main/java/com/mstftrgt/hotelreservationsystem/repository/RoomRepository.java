package com.mstftrgt.hotelreservationsystem.repository;

import com.mstftrgt.hotelreservationsystem.domain.Repository;
import com.mstftrgt.hotelreservationsystem.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends Repository {


    Optional<Room> findById(long roomId);
    void save(String roomNumber, long roomTypeId);

    List<Room> getAllRoomsOfRoomType(long roomTypeId);

    void deleteById(long roomId);
}
