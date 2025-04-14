package com.mstftrgt.hotelreservationsystem.command.room.remove;

import com.mstftrgt.hotelreservationsystem.cqrs.Command;
import lombok.Builder;
import java.util.UUID;

@Builder
public record RemoveRoomCommand(UUID roomId) implements Command {
}