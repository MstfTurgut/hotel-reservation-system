package com.mstftrgt.hotelreservationsystem.query.room.findbyid;

import com.mstftrgt.hotelreservationsystem.exception.RoomNotFoundException;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryHandler;
import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomReadModel;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindRoomByIdQueryHandler implements QueryHandler<FindRoomByIdQuery, RoomReadModel> {

    private final RoomRepository roomRepository;

    @Override
    public RoomReadModel handle(FindRoomByIdQuery query) {
        Room room = roomRepository.findById(query.roomId())
                .orElseThrow(() -> new RoomNotFoundException(query.roomId()));

        return RoomReadModel.from(room);
    }
}
