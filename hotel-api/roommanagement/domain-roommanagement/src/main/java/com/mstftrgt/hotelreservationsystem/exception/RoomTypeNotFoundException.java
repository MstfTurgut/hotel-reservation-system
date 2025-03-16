package com.mstftrgt.hotelreservationsystem.exception;

import java.util.UUID;

public class RoomTypeNotFoundException extends RuntimeException {
    public RoomTypeNotFoundException(UUID roomTypeId) {
        super("RoomType not found with id : " + roomTypeId);
    }
}

