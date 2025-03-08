package com.mstftrgt.hotelreservationsystem.command.roomtype.decrementnumberofrooms;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Data;

@Data
public class DecrementNumberOfRoomsCommand implements Command {
    private final long roomTypeId;
}
