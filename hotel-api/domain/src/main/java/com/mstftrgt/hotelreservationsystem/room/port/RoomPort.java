package com.mstftrgt.hotelreservationsystem.room.port;

import com.mstftrgt.hotelreservationsystem.room.model.Room;

public interface RoomPort {

    Room save(Room room);
    void deleteById(long roomId);

}
