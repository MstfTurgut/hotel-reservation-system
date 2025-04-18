package com.mstftrgt.hotelreservationsystem.presentation.dto;

import com.mstftrgt.hotelreservationsystem.command.roomtype.modify.ModifyRoomTypeCommand;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record ModifyRoomTypeRequest(

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        @Positive
        BigDecimal priceForNight,

        @Min(1)
        int adultCapacity,

        @Min(0)
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
