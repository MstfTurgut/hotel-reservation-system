package com.mstftrgt.hotelreservationsystem.query.reservation.findavailabilitiesforroomtypes;

import com.mstftrgt.hotelreservationsystem.facade.RoomManagementFacade;
import com.mstftrgt.hotelreservationsystem.contract.RoomTypeContract;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationAvailabilityForRoomTypeReadModel;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomTypeInfoReadModel;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.service.PriceCalculationService;
import com.mstftrgt.hotelreservationsystem.reservation.service.ReservationAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;
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

        List<RoomTypeContract> suitableRoomTypes = roomManagementFacade
                .findAllSuitableRoomTypes(query.adultGuestCount(), query.childGuestCount());

        StayDate requestedStay = Reservation.createValidStayDate(query.checkInDate(), query.checkOutDate());

        return suitableRoomTypes.stream()
                .map(roomType -> {
                    List<UUID> roomIdsOfRoomType = roomManagementFacade.findAllRoomIdsForRoomType(roomType.id());

                    int numberOfAvailableRoomsForReservation =
                            getNumberOfAvailableRoomsForReservation(roomIdsOfRoomType, requestedStay);

                    BigDecimal totalPrice = calculatePrice(roomType, requestedStay);

                    return buildReservationAvailability(roomType, numberOfAvailableRoomsForReservation, totalPrice);
                }).toList();
    }

    private int getNumberOfAvailableRoomsForReservation(List<UUID> roomIdsOfRoomType, StayDate requestedStay) {
        return reservationAvailabilityService.findNumberOfAvailableRoomsToReserveForStayDate(roomIdsOfRoomType, requestedStay);
    }

    private BigDecimal calculatePrice(RoomTypeContract roomType, StayDate requestedStay) {
        return priceCalculationService.calculateTotalPrice(roomType.priceForNight(), requestedStay);
    }

    private ReservationAvailabilityForRoomTypeReadModel buildReservationAvailability(RoomTypeContract roomType, int numberOfAvailableRoomsForReservation, BigDecimal totalPrice) {
        return ReservationAvailabilityForRoomTypeReadModel
                .builder()
                .roomTypeInfoReadModel(RoomTypeInfoReadModel.builder()
                        .roomTypeTitle(roomType.title())
                        .roomTypeDescription(roomType.description())
                        .roomTypeAdultCapacity(roomType.adultCapacity())
                        .roomTypeChildCapacity(roomType.childCapacity())
                        .roomTypePriceForNight(roomType.priceForNight()).build())
                .remainingRooms(numberOfAvailableRoomsForReservation)
                .totalPriceForStayDate(totalPrice)
                .build();
    }
}
