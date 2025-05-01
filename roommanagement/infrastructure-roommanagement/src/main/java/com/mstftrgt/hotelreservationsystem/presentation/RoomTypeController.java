package com.mstftrgt.hotelreservationsystem.presentation;

import com.mstftrgt.hotelreservationsystem.command.roomtype.remove.RemoveRoomTypeCommand;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBus;
import com.mstftrgt.hotelreservationsystem.presentation.dto.AddNewRoomTypeRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.ModifyRoomTypeRequest;
import com.mstftrgt.hotelreservationsystem.query.room.findallforroomtype.FindAllRoomsOfRoomTypeQuery;
import com.mstftrgt.hotelreservationsystem.query.roomtype.findall.FindAllRoomTypesQuery;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomReadModel;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomTypeReadModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room-types")
public class RoomTypeController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UUID addNewRoomType(@Valid @RequestBody AddNewRoomTypeRequest request) {
        return commandBus.dispatchAndReturn(request.toCommand());
    }

    @PutMapping("/{roomTypeId}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyRoomType(@PathVariable UUID roomTypeId, @Valid @RequestBody ModifyRoomTypeRequest request) {
        commandBus.dispatch(request.toCommand(roomTypeId));
    }

    @DeleteMapping("/{roomTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoomType(@PathVariable UUID roomTypeId) {
        commandBus.dispatch(new RemoveRoomTypeCommand(roomTypeId));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RoomTypeReadModel> getAllRoomTypes() {
        return queryBus.dispatchAndReturn(new FindAllRoomTypesQuery());
    }

    @GetMapping("{roomTypeId}/rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomReadModel> getAllRoomsOfRoomType(@PathVariable UUID roomTypeId) {
        return queryBus.dispatchAndReturn(new FindAllRoomsOfRoomTypeQuery(roomTypeId));
    }
}
