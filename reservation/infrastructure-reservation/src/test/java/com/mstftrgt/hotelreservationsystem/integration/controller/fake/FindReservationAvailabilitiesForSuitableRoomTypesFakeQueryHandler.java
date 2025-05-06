package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.generic.application.QueryHandler;
import com.mstftrgt.hotelreservationsystem.query.reservation.findavailabilitiesforroomtypes.FindReservationAvailabilitiesForSuitableRoomTypesQuery;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationAvailabilityForRoomTypeReadModel;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomTypeInfoReadModel;
import com.mstftrgt.hotelreservationsystem.reservation.exception.InvalidStayDateException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.INVALID_STAY_DATE_LOCAL_DATE;

@Service
public class FindReservationAvailabilitiesForSuitableRoomTypesFakeQueryHandler implements QueryHandler<FindReservationAvailabilitiesForSuitableRoomTypesQuery, List<ReservationAvailabilityForRoomTypeReadModel>> {


    @Override
    public List<ReservationAvailabilityForRoomTypeReadModel> handle(FindReservationAvailabilitiesForSuitableRoomTypesQuery query) {
        if (query.checkInDate().equals(INVALID_STAY_DATE_LOCAL_DATE)) {
            throw new InvalidStayDateException("Check-in date cannot be in the past.");
        }

        return List.of(
                ReservationAvailabilityForRoomTypeReadModel.builder()
                        .roomTypeInfoReadModel(RoomTypeInfoReadModel.builder()
                                .roomTypeTitle("testRoomTypeTitle")
                                .roomTypeDescription("testRoomTypeDescription")
                                .roomTypePriceForNight(BigDecimal.ONE)
                                .roomTypeAdultCapacity(2)
                                .roomTypeChildCapacity(1)
                                .build())
                        .remainingRooms(5)
                        .totalPriceForStayDate(BigDecimal.TEN)
                        .build()
        );
    }
}
