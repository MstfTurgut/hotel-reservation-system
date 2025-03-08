package com.mstftrgt.hotelreservationsystem.command.roomtype.addnew;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AddNewRoomTypeCommand implements Command {
    private final String title;
    private final String description;
    private final BigDecimal priceForNight;
    private final int adultCapacity;
    private final int childCapacity;
}