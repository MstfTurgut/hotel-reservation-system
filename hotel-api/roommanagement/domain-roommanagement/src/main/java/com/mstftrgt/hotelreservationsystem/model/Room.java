package com.mstftrgt.hotelreservationsystem.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room {

    private Long id;
    private Long roomTypeId;
    private String roomNumber;
}
