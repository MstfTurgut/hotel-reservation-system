package com.mstftrgt.hotelreservationsystem.command.roomtype.incrementnumberofrooms;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Data;

@Data
public class IncrementNumberOfRoomsCommand implements Command {
    private final long roomTypeId;
}
