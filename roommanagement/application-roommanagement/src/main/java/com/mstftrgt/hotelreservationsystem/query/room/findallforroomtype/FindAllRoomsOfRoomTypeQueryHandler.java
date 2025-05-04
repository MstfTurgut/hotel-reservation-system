package com.mstftrgt.hotelreservationsystem.query.room.findallforroomtype;

import com.mstftrgt.hotelreservationsystem.generic.application.QueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomReadModel;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllRoomsOfRoomTypeQueryHandler implements QueryHandler<FindAllRoomsOfRoomTypeQuery, List<RoomReadModel>> {

    private final RoomRepository roomRepository;

    @Override
    public List<RoomReadModel> handle(FindAllRoomsOfRoomTypeQuery query) {
        return roomRepository.getAllRoomsOfRoomType(query.roomTypeId())
                .stream()
                .map(RoomReadModel::from)
                .toList();
    }
}