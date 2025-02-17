package com.mstftrgt.hotelreservationsystem.room.port;

import com.mstftrgt.hotelreservationsystem.room.model.Room;

import java.util.Optional;

public interface RoomPort {

    Room save(Room room);
    void deleteById(long roomId);

    Optional<Room> findById(long roomId);

}
