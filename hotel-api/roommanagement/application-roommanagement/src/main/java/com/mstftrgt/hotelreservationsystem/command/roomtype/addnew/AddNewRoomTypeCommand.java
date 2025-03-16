package com.mstftrgt.hotelreservationsystem.command.roomtype.addnew;

import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.dto.RoomTypeCreate;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
public record AddNewRoomTypeCommand(
        String title,
        String description,
        BigDecimal priceForNight,
        int adultCapacity,
        int childCapacity) implements Command {

    public RoomTypeCreate toRoomTypeCreate() {
        return RoomTypeCreate.builder()
                .title(title)
                .description(description)
                .priceForNight(priceForNight)
                .adultCapacity(adultCapacity)
                .childCapacity(childCapacity)
                .build();
    }

}