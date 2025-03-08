package com.mstftrgt.hotelreservationsystem.command.roomtype.modify;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ModifyRoomTypeCommand implements Command {

    private final Long id;
    private final String title;
    private final String description;
    private final BigDecimal priceForNight;
    private final int adultCapacity;
    private final int childCapacity;
}