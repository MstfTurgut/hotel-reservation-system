package com.mstftrgt.hotelreservationsystem.presentation;


import com.mstftrgt.hotelreservationsystem.command.room.remove.RemoveRoomCommand;
import com.mstftrgt.hotelreservationsystem.cqrs.CommandBus;
import com.mstftrgt.hotelreservationsystem.presentation.dto.AddNewRoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {

    private final CommandBus commandBus;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewRoom(@RequestBody AddNewRoomRequest request) {
        commandBus.dispatch(request.toCommand());
    }

    @DeleteMapping("/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoom(@PathVariable UUID roomId) {
        commandBus.dispatch(new RemoveRoomCommand(roomId));
    }
}
