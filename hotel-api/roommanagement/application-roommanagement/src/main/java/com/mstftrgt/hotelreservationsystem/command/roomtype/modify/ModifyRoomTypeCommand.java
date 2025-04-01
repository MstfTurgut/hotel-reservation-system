package com.mstftrgt.hotelreservationsystem.command.roomtype.modify;

import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.dto.RoomTypeModify;
import lombok.Builder;
import lombok.Data;

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