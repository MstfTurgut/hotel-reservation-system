package com.mstftrgt.hotelreservationsystem.command.roomtype.modify;

import com.mstftrgt.hotelreservationsystem.cqrs.Command;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ModifyRoomTypeCommand(
        UUID roomTypeId,
        String title,
        String description,
        BigDecimal priceForNight,
        int adultCapacity,
        int childCapacity) implements Command {

}