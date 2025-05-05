package com.mstftrgt.hotelreservationsystem.presentation;


import com.mstftrgt.hotelreservationsystem.command.room.remove.RemoveRoomCommand;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBus;
import com.mstftrgt.hotelreservationsystem.presentation.dto.AddNewRoomRequest;
import com.mstftrgt.hotelreservationsystem.query.room.findallforroomtype.FindAllRoomsOfRoomTypeQuery;
import com.mstftrgt.hotelreservationsystem.query.room.findbyid.FindRoomByIdQuery;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomReadModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final QueryBus queryBus;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UUID addNewRoom(@Valid @RequestBody AddNewRoomRequest request) {
        return commandBus.dispatchAndReturn(request.toCommand());
    }

    @DeleteMapping("/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoom(@PathVariable UUID roomId) {
        commandBus.dispatch(new RemoveRoomCommand(roomId));
    }

    @GetMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public RoomReadModel findRoomById(@PathVariable UUID roomId) {
        return queryBus.dispatchAndReturn(new FindRoomByIdQuery(roomId));
    }
}
