package com.mstftrgt.hotelreservationsystem.query.roomtype.findall;

import com.mstftrgt.hotelreservationsystem.QueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomTypeReadModel;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllRoomTypesQueryHandler implements QueryHandler<FindAllRoomTypesQuery, List<RoomTypeReadModel>> {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public List<RoomTypeReadModel> handle(FindAllRoomTypesQuery query) {
        return roomTypeRepository.findAll()
                .stream()
                .map(RoomTypeReadModel::from)
                .toList();
    }
}
