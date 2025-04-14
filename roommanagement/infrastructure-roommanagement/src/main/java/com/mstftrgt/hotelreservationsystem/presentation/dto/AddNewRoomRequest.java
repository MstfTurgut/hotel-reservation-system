package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.command.room.addnew.AddNewRoomCommand;

import java.util.UUID;

public record AddNewRoomRequest(String roomNumber, UUID roomTypeId) {

    public AddNewRoomCommand toCommand() {
        return AddNewRoomCommand.builder()
                .roomNumber(roomNumber)
                .roomTypeId(roomTypeId)
                .build();
    }


}
