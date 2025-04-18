package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.command.room.addnew.AddNewRoomCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddNewRoomRequest(

        @NotBlank
        String roomNumber,

        @NotNull
        UUID roomTypeId

) {
    public AddNewRoomCommand toCommand() {
        return AddNewRoomCommand.builder()
                .roomNumber(roomNumber)
                .roomTypeId(roomTypeId)
                .build();
    }
}