package com.mstftrgt.hotelreservationsystem.command.room.addnew;

import com.mstftrgt.hotelreservationsystem.generic.application.Command;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AddNewRoomCommand(
        String roomNumber,
        UUID roomTypeId

) implements Command {}