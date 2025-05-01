package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.command.room.addnew.AddNewRoomCommand;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandHandler;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddNewRoomFakeCommandHandler implements CommandHandler<AddNewRoomCommand, UUID> {
    @Override
    public UUID handle(AddNewRoomCommand command) {
        return UUID.randomUUID();
    }
}
