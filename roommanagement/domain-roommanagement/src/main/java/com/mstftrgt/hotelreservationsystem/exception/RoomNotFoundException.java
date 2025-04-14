package com.mstftrgt.hotelreservationsystem.exception;

import java.util.UUID;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(UUID roomId) {
        super("Room not found with id : " + roomId);
    }
}
