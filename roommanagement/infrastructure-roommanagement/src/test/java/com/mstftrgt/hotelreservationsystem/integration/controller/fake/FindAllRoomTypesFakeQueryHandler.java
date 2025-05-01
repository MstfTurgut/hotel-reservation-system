package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.generic.application.QueryHandler;
import com.mstftrgt.hotelreservationsystem.query.roomtype.findall.FindAllRoomTypesQuery;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomTypeReadModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class FindAllRoomTypesFakeQueryHandler implements QueryHandler<FindAllRoomTypesQuery, List<RoomTypeReadModel>> {

    @Override
    public List<RoomTypeReadModel> handle(FindAllRoomTypesQuery query) {
        return List.of(
                    RoomTypeReadModel.builder()
                            .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                            .title("testTitle")
                            .description("testDescription")
                            .priceForNight(BigDecimal.TEN)
                            .adultCapacity(2)
                            .childCapacity(1)
                            .build()
        );
    }
}
