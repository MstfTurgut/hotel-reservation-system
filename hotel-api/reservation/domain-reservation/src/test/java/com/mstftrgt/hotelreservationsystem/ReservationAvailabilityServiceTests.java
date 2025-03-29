package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotAvailableException;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.ReservationStatus;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import com.mstftrgt.hotelreservationsystem.reservation.service.ReservationAvailabilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class ReservationAvailabilityServiceTests {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationAvailabilityService reservationAvailabilityService;

    UUID roomId1;
    UUID roomId2;
    UUID roomId3;
    StayDate testRequestedStay;
    Reservation testOverlappingReservation;
    Reservation testNonoverlappingReservation;

    Reservation testCancelledOverlappingReservation;


    @BeforeEach
    void setUp() {
        roomId1 = UUID.fromString("00000000-0000-0000-0000-000000000001");
        roomId2 = UUID.fromString("00000000-0000-0000-0000-000000000002");
        roomId3 = UUID.fromString("00000000-0000-0000-0000-000000000003");

        testRequestedStay = new StayDate(
                LocalDate.of(2030, 1, 1),
                LocalDate.of(2030, 1, 5));

        testOverlappingReservation = ReservationTestDataFactory.getTestReservationWith(new StayDate(
                LocalDate.of(2030, 1, 1),
                LocalDate.of(2030, 1, 3)));

        testNonoverlappingReservation = ReservationTestDataFactory.getTestReservationWith(new StayDate(
                LocalDate.of(2030, 1, 7),
                LocalDate.of(2030, 1, 10)));

        testCancelledOverlappingReservation = testOverlappingReservation.withStatus(ReservationStatus.CANCELLED);
    }

    @Test
    public void findAvailableRoomToReserveForStayDate_shouldReturnRoomId_whenARoomIsAvailableForStayDate() {
        when(reservationRepository.findReservationsOfRoom(roomId1)).thenReturn(List.of(testOverlappingReservation));
        when(reservationRepository.findReservationsOfRoom(roomId2)).thenReturn(List.of(testNonoverlappingReservation));
        lenient().when(reservationRepository.findReservationsOfRoom(roomId3)).thenReturn(List.of(testOverlappingReservation));

        List<UUID> roomIdList = List.of(roomId1, roomId2, roomId3);

        UUID roomIdResult = reservationAvailabilityService.findAvailableRoomToReserveForStayDate(roomIdList, testRequestedStay);

        assertEquals(roomId2, roomIdResult);
    }

    @Test
    public void findAvailableRoomToReserveForStayDate_shouldReturnFirstAvailableRoomId_whenMultipleRoomsAreAvailableForStayDate() {
        when(reservationRepository.findReservationsOfRoom(roomId1)).thenReturn(List.of(testNonoverlappingReservation));
        lenient().when(reservationRepository.findReservationsOfRoom(roomId2)).thenReturn(List.of(testNonoverlappingReservation));
        lenient().when(reservationRepository.findReservationsOfRoom(roomId3)).thenReturn(List.of(testOverlappingReservation));

        List<UUID> roomIdList = List.of(roomId1, roomId2, roomId3);

        UUID roomIdResult = reservationAvailabilityService.findAvailableRoomToReserveForStayDate(roomIdList, testRequestedStay);

        assertEquals(roomId1, roomIdResult);
    }

    @Test
    public void findAvailableRoomToReserveForStayDate_shouldThrowException_whenThereIsNoAvailableRoomForStayDate() {
        when(reservationRepository.findReservationsOfRoom(roomId1)).thenReturn(List.of(testOverlappingReservation));
        when(reservationRepository.findReservationsOfRoom(roomId2)).thenReturn(List.of(testOverlappingReservation));
        when(reservationRepository.findReservationsOfRoom(roomId3)).thenReturn(List.of(testOverlappingReservation));

        List<UUID> roomIdList = List.of(roomId1, roomId2, roomId3);

        assertThrows(ReservationNotAvailableException.class, () ->
                reservationAvailabilityService.findAvailableRoomToReserveForStayDate(roomIdList, testRequestedStay));
    }

    @Test
    public void findAvailableRoomToReserveForStayDate_shouldThrowException_whenRoomListIsEmpty() {
        List<UUID> roomIdList = List.of();

        assertThrows(ReservationNotAvailableException.class, () ->
                reservationAvailabilityService.findAvailableRoomToReserveForStayDate(roomIdList, testRequestedStay));
    }

    @Test
    public void findAvailableRoomToReserveForStayDate_shouldReturnRoomId_whenARoomHasNoReservations() {
        when(reservationRepository.findReservationsOfRoom(roomId1)).thenReturn(List.of());

        List<UUID> roomIdList = List.of(roomId1);

        UUID roomIdResult = reservationAvailabilityService.findAvailableRoomToReserveForStayDate(roomIdList, testRequestedStay);

        assertEquals(roomId1, roomIdResult);
    }

    @Test
    public void findAvailableRoomToReserveForStayDate_shouldReturnRoomId_whenARoomHaveCancelledOverlappingReservation() {
        when(reservationRepository.findReservationsOfRoom(roomId1)).thenReturn(List.of(testCancelledOverlappingReservation));

        List<UUID> roomIdList = List.of(roomId1);

        UUID roomIdResult = reservationAvailabilityService.findAvailableRoomToReserveForStayDate(roomIdList, testRequestedStay);

        assertEquals(roomId1, roomIdResult);
    }

    @Test
    public void findNumberOfAvailableRoomsToReserveForStayDate_shouldReturnTheSizeOfList_whenAllRoomsAreAvailableForStayDate() {
        when(reservationRepository.findReservationsOfRoom(roomId1)).thenReturn(List.of(testNonoverlappingReservation));
        when(reservationRepository.findReservationsOfRoom(roomId2)).thenReturn(List.of(testNonoverlappingReservation));
        when(reservationRepository.findReservationsOfRoom(roomId3)).thenReturn(List.of(testNonoverlappingReservation));

        List<UUID> roomIdList = List.of(roomId1, roomId2, roomId3);

        int numberOfAvailableRoomsResult = reservationAvailabilityService.findNumberOfAvailableRoomsToReserveForStayDate(roomIdList, testRequestedStay);

        assertEquals(roomIdList.size(), numberOfAvailableRoomsResult);
    }

    @Test
    public void findNumberOfAvailableRoomsToReserveForStayDate_shouldReturnTheCorrectCount_whenSomeRoomsAreAvailableForStayDate() {
        when(reservationRepository.findReservationsOfRoom(roomId1)).thenReturn(List.of(testNonoverlappingReservation));
        when(reservationRepository.findReservationsOfRoom(roomId2)).thenReturn(List.of(testNonoverlappingReservation));
        when(reservationRepository.findReservationsOfRoom(roomId3)).thenReturn(List.of(testOverlappingReservation));

        List<UUID> roomIdList = List.of(roomId1, roomId2, roomId3);

        int numberOfAvailableRoomsResult = reservationAvailabilityService.findNumberOfAvailableRoomsToReserveForStayDate(roomIdList, testRequestedStay);

        assertEquals(2, numberOfAvailableRoomsResult);
    }

    @Test
    public void findNumberOfAvailableRoomsToReserveForStayDate_shouldReturnZero_whenNoneOfTheRoomsAreAvailableForStayDate() {
        when(reservationRepository.findReservationsOfRoom(roomId1)).thenReturn(List.of(testOverlappingReservation));
        when(reservationRepository.findReservationsOfRoom(roomId2)).thenReturn(List.of(testOverlappingReservation));
        when(reservationRepository.findReservationsOfRoom(roomId3)).thenReturn(List.of(testOverlappingReservation));

        List<UUID> roomIdList = List.of(roomId1, roomId2, roomId3);

        int numberOfAvailableRoomsResult = reservationAvailabilityService.findNumberOfAvailableRoomsToReserveForStayDate(roomIdList, testRequestedStay);

        assertEquals(0, numberOfAvailableRoomsResult);
    }

    @Test
    public void findNumberOfAvailableRoomsToReserveForStayDate_shouldReturnZero_whenRoomListIsEmpty() {
        List<UUID> roomIdList = List.of();

        int numberOfAvailableRoomsResult = reservationAvailabilityService.findNumberOfAvailableRoomsToReserveForStayDate(roomIdList, testRequestedStay);

        assertEquals(0, numberOfAvailableRoomsResult);
    }

    @Test
    public void findNumberOfAvailableRoomsToReserveForStayDate_shouldReturnTheCorrectCount_whenARoomHasCancelledOverlappingReservation() {
        when(reservationRepository.findReservationsOfRoom(roomId1)).thenReturn(List.of(testNonoverlappingReservation));
        when(reservationRepository.findReservationsOfRoom(roomId2)).thenReturn(List.of(testCancelledOverlappingReservation));
        when(reservationRepository.findReservationsOfRoom(roomId3)).thenReturn(List.of(testOverlappingReservation));

        List<UUID> roomIdList = List.of(roomId1, roomId2, roomId3);

        int numberOfAvailableRoomsResult = reservationAvailabilityService.findNumberOfAvailableRoomsToReserveForStayDate(roomIdList, testRequestedStay);

        assertEquals(2, numberOfAvailableRoomsResult);
    }

    @Test
    public void findNumberOfAvailableRoomsToReserveForStayDate_shouldReturnTheCorrectCount_whenARoomHasNoReservations() {
        when(reservationRepository.findReservationsOfRoom(roomId1)).thenReturn(List.of(testNonoverlappingReservation));
        when(reservationRepository.findReservationsOfRoom(roomId2)).thenReturn(List.of());
        when(reservationRepository.findReservationsOfRoom(roomId3)).thenReturn(List.of(testOverlappingReservation));

        List<UUID> roomIdList = List.of(roomId1, roomId2, roomId3);

        int numberOfAvailableRoomsResult = reservationAvailabilityService.findNumberOfAvailableRoomsToReserveForStayDate(roomIdList, testRequestedStay);

        assertEquals(2, numberOfAvailableRoomsResult);
    }
}