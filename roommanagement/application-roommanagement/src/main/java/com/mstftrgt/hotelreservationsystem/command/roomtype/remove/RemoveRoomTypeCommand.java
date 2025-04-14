package com.mstftrgt.hotelreservationsystem.command.roomtype.remove;

import com.mstftrgt.hotelreservationsystem.cqrs.Command;
import lombok.Builder;

import java.util.UUID;

@Builder
public record RemoveRoomTypeCommand(
        UUID roomTypeId
) implements Command {}
