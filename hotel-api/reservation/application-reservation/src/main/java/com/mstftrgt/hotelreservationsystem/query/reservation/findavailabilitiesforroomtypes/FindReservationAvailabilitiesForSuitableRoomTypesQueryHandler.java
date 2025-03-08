package com.mstftrgt.hotelreservationsystem.query.reservation.findavailabilitiesforroomtypes;

import com.mstftrgt.hotelreservationsystem.QueryHandler;
import com.mstftrgt.hotelreservationsystem.facade.RoomManagementFacade;
import com.mstftrgt.hotelreservationsystem.facade.RoomTypeContract;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationAvailabilityForRoomTypeReadModel;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.service.PriceCalculationService;
import com.mstftrgt.hotelreservationsystem.reservation.service.ReservationAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindReservationAvailabilitiesForSuitableRoomTypesQueryHandler implements QueryHandler<FindReservationAvailabilitiesForSuitableRoomTypesQuery, List<ReservationAvailabilityForRoomTypeReadModel>> {

    private final RoomManagementFacade roomManagementFacade;
    private final ReservationAvailabilityService reservationAvailabilityService;
    private final PriceCalculationService priceCalculationService;

    @Override
    public List<ReservationAvailabilityForRoomTypeReadModel> handle(FindReservationAvailabilitiesForSuitableRoomTypesQuery query) {

        List<RoomTypeContract> suitableRoomTypes = roomManagementFacade.findAllSuitableRoomTypes(query.adultGuestCount(), query.childGuestCount());

        return suitableRoomTypes.stream()
                .map(roomType -> {
                    List<Long> roomIdsOfRoomType = roomManagementFacade.findAllRoomIdsForRoomType(roomType.id());

                    int numberOfAvailableRoomsForReservation =
                            getNumberOfAvailableRoomsForReservation(query, roomIdsOfRoomType);

                    BigDecimal totalPrice = calculatePrice(query, roomType);

                    return buildReservationAvailability(roomType, numberOfAvailableRoomsForReservation, totalPrice);
                }).toList();
    }

    private int getNumberOfAvailableRoomsForReservation(FindReservationAvailabilitiesForSuitableRoomTypesQuery query, List<Long> roomIdsOfRoomType) {
        return reservationAvailabilityService
                .getNumberOfAvailableRoomsForReservation(roomIdsOfRoomType,
                        query.checkInDate(), query.checkOutDate());
    }

    private BigDecimal calculatePrice(FindReservationAvailabilitiesForSuitableRoomTypesQuery query, RoomTypeContract roomType) {
        return priceCalculationService.calculateTotalPrice(roomType.priceForNight(),
                new StayDate(query.checkInDate(), query.checkOutDate()));
    }

    private static ReservationAvailabilityForRoomTypeReadModel buildReservationAvailability(RoomTypeContract roomType, int numberOfAvailableRoomsForReservation, BigDecimal totalPrice) {
        return ReservationAvailabilityForRoomTypeReadModel
                .builder()
                .roomTypeTitle(roomType.title())
                .roomTypeDescription(roomType.description())
                .roomTypeAdultCapacity(roomType.adultCapacity())
                .roomTypeChildCapacity(roomType.childCapacity())
                .roomTypePriceForNight(roomType.priceForNight())
                .remainingRooms(numberOfAvailableRoomsForReservation)
                .totalPrice(totalPrice)
                .build();
    }
}
