package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.command.roomtype.addnew.AddNewRoomTypeCommand;

import java.math.BigDecimal;

public record AddNewRoomTypeRequest(
        String title,
        String description,
        BigDecimal priceForNight,
        int adultCapacity,
        int childCapacity
) {

    public AddNewRoomTypeCommand toCommand() {
        return AddNewRoomTypeCommand.builder()
                .title(title)
                .description(description)
                .priceForNight(priceForNight)
                .adultCapacity(adultCapacity)
                .childCapacity(childCapacity)
                .build();
    }
}
