package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.command.roomtype.addnew.AddNewRoomTypeCommand;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandHandler;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddNewRoomTypeFakeCommandHandler implements CommandHandler<AddNewRoomTypeCommand, UUID> {

    @Override
    public UUID handle(AddNewRoomTypeCommand command) {
        return UUID.randomUUID();
    }
}
