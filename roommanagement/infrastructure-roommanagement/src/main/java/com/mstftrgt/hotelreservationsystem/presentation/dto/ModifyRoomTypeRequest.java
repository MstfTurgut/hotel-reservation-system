package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.command.roomtype.modify.ModifyRoomTypeCommand;

import java.math.BigDecimal;
import java.util.UUID;

public record ModifyRoomTypeRequest(
        String title,
        String description,
        BigDecimal priceForNight,
        int adultCapacity,
        int childCapacity
) {

    public ModifyRoomTypeCommand toCommand(UUID roomTypeId) {
        return ModifyRoomTypeCommand.builder()
                .roomTypeId(roomTypeId)
                .title(title)
                .description(description)
                .priceForNight(priceForNight)
                .adultCapacity(adultCapacity)
                .childCapacity(childCapacity)
                .build();
    }

}
