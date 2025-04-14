package com.mstftrgt.hotelreservationsystem.command.roomtype.addnew;

import com.mstftrgt.hotelreservationsystem.cqrs.Command;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AddNewRoomTypeCommand(
        String title,
        String description,
        BigDecimal priceForNight,
        int adultCapacity,
        int childCapacity) implements Command {}