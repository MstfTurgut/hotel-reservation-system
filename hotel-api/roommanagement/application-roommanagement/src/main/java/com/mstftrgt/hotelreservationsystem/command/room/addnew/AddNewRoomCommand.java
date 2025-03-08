package com.mstftrgt.hotelreservationsystem.command.room.addnew;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddNewRoomCommand implements Command {
    private final String roomNumber;
    private final Long roomTypeId;
}