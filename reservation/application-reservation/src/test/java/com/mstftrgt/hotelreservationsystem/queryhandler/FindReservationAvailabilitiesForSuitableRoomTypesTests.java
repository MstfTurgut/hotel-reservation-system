package com.mstftrgt.hotelreservationsystem.queryhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.facade.RoomManagementFacade;
import com.mstftrgt.hotelreservationsystem.contract.RoomTypeContract;
import com.mstftrgt.hotelreservationsystem.query.reservation.findavailabilitiesforroomtypes.FindReservationAvailabilitiesForSuitableRoomTypesQuery;
import com.mstftrgt.hotelreservationsystem.query.reservation.findavailabilitiesforroomtypes.FindReservationAvailabilitiesForSuitableRoomTypesQueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationAvailabilityForRoomTypeReadModel;
import com.mstftrgt.hotelreservationsystem.readmodel.RoomTypeInfoReadModel;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.service.PriceCalculationService;
import com.mstftrgt.hotelreservationsystem.reservation.service.ReservationAvailabilityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindReservationAvailabilitiesForSuitableRoomTypesTests {

    @Mock
    private RoomManagementFacade roomManagementFacade;

    @Mock
    private ReservationAvailabilityService reservationAvailabilityService;

    @Mock
    private PriceCalculationService priceCalculationService;

    @InjectMocks
    private FindReservationAvailabilitiesForSuitableRoomTypesQueryHandler handler;

    @Test
    void shouldReturnReservationAvailabilitiesForSuitableRoomTypes() {
        FindReservationAvailabilitiesForSuitableRoomTypesQuery query = ApplicationTestDataFactory
                .getFindReservationAvailabilitiesForSuitableRoomTypesTestQuery();

        RoomTypeContract roomType = RoomTypeContract.builder()
                .id(UUID.randomUUID())
                .title("Deluxe Room")
                .description("Spacious room with garden view")
                .priceForNight(BigDecimal.TEN)
                .numberOfRooms(2)
                .adultCapacity(2)
                .childCapacity(1)
                .build();

        List<RoomTypeContract> suitableRoomTypes = List.of(roomType);

        StayDate requestedStay = new StayDate(query.checkInDate(), query.checkOutDate());

        List<UUID> roomIdsOfRoomType = List.of(UUID.randomUUID(), UUID.randomUUID());
        int remainingAvailableRooms = 1;
        BigDecimal totalPriceOfRoomTypeForStayDate = BigDecimal.TEN;


        when(roomManagementFacade.findAllSuitableRoomTypes(query.adultGuestCount(), query.childGuestCount()))
                .thenReturn(suitableRoomTypes);

        mockStatic(Reservation.class).when(() -> Reservation.createValidStayDate(query.checkInDate(), query.checkOutDate()))
                    .thenReturn(requestedStay);

        when(roomManagementFacade.findAllRoomIdsForRoomType(roomType.id()))
                .thenReturn(roomIdsOfRoomType);

        when(reservationAvailabilityService.findNumberOfAvailableRoomsToReserveForStayDate(roomIdsOfRoomType, requestedStay))
                    .thenReturn(remainingAvailableRooms);

        when(priceCalculationService.calculateTotalPrice(roomType.priceForNight(), requestedStay))
                    .thenReturn(totalPriceOfRoomTypeForStayDate);


        RoomTypeInfoReadModel roomTypeInfo = RoomTypeInfoReadModel.builder()
                .roomTypeTitle(roomType.title())
                .roomTypeDescription(roomType.description())
                .roomTypeAdultCapacity(roomType.adultCapacity())
                .roomTypeChildCapacity(roomType.childCapacity())
                .roomTypePriceForNight(roomType.priceForNight())
                .build();

        ReservationAvailabilityForRoomTypeReadModel expected = ReservationAvailabilityForRoomTypeReadModel.builder()
                .roomTypeInfoReadModel(roomTypeInfo)
                .remainingRooms(remainingAvailableRooms)
                .totalPriceForStayDate(BigDecimal.TEN)
                .build();


        List<ReservationAvailabilityForRoomTypeReadModel> result = handler.handle(query);

        assertEquals(1, result.size());
        assertEquals(expected, result.get(0));
    }

    @Test
    void shouldReturnEmptyListWhenNoSuitableRoomTypes() {
        FindReservationAvailabilitiesForSuitableRoomTypesQuery query = ApplicationTestDataFactory
                .getFindReservationAvailabilitiesForSuitableRoomTypesTestQuery();

        when(roomManagementFacade.findAllSuitableRoomTypes(query.adultGuestCount(), query.childGuestCount()))
                .thenReturn(List.of());

        List<ReservationAvailabilityForRoomTypeReadModel> result = handler.handle(query);

        assertEquals(0, result.size());
    }
}