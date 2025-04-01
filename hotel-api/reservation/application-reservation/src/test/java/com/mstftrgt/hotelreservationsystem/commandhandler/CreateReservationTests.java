package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.reservation.create.CreateReservationCommand;
import com.mstftrgt.hotelreservationsystem.command.reservation.create.CreateReservationCommandHandler;
import com.mstftrgt.hotelreservationsystem.facade.RoomManagementFacade;
import com.mstftrgt.hotelreservationsystem.reservation.dto.ReservationCreate;
import com.mstftrgt.hotelreservationsystem.reservation.exception.InvalidStayDateException;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import com.mstftrgt.hotelreservationsystem.reservation.service.ConfirmationCodeGenerationService;
import com.mstftrgt.hotelreservationsystem.reservation.service.ReservationAvailabilityService;
import com.mstftrgt.hotelreservationsystem.reservation.service.ReservationCodeGenerationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateReservationTests {

    @Mock
    Reservation reservation;

    @Mock
    private RoomManagementFacade roomManagementFacade;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationAvailabilityService reservationAvailabilityService;

    @Mock
    private ReservationCodeGenerationService reservationCodeGenerationService;

    @Mock
    private ConfirmationCodeGenerationService confirmationCodeGenerationService;

    @InjectMocks
    private CreateReservationCommandHandler handler;

    @Test
    void shouldCreateReservationSuccessfully() {
        CreateReservationCommand command = ApplicationTestDataFactory.getCreateReservationTestCommand();

        StayDate stayDate = new StayDate(command.checkInDate(), command.checkOutDate());
        String confirmationCode = "testConfirmationCode";
        String reservationCode = "testReservationCode";
        UUID testRoomId = UUID.randomUUID();

        try (MockedStatic<Reservation> reservationStatic = mockStatic(Reservation.class)) {

            reservationStatic.when(() -> Reservation.createValidStayDate(command.checkInDate(), command.checkOutDate()))
                    .thenReturn(stayDate);

            when(roomManagementFacade.findAllRoomIdsForRoomType(command.roomTypeId()))
                    .thenReturn(List.of(testRoomId));

            when(reservationAvailabilityService.findAvailableRoomToReserveForStayDate(List.of(testRoomId), stayDate))
                    .thenReturn(testRoomId);

            when(confirmationCodeGenerationService.generateConfirmationCode())
                    .thenReturn(confirmationCode);

            when(reservationCodeGenerationService.generateReservationCode())
                    .thenReturn(reservationCode);

            reservationStatic.when(() -> Reservation.create(any(ReservationCreate.class)))
                    .thenReturn(reservation);

            handler.handle(command);

            verify(reservationRepository).save(reservation);
        }
    }

    @Test
    void shouldNotSaveIfCreateValidStayDateThrows() {
        CreateReservationCommand command = ApplicationTestDataFactory.getCreateReservationTestCommand();

        try (MockedStatic<Reservation> reservationStatic = mockStatic(Reservation.class)) {
            reservationStatic.when(() -> Reservation.createValidStayDate(command.checkInDate(), command.checkOutDate()))
                    .thenThrow(new InvalidStayDateException("stay date cannot be in past"));

            assertThrows(InvalidStayDateException.class, () -> handler.handle(command));

            verifyNoInteractions(roomManagementFacade);
            verifyNoInteractions(reservationAvailabilityService);
            verifyNoInteractions(reservationCodeGenerationService);
            verifyNoInteractions(confirmationCodeGenerationService);
            verifyNoInteractions(reservationRepository);
        }
    }

    @Test
    void shouldNotSaveIfFindAvailableRoomToReserveForStayDateThrows() {
        CreateReservationCommand command = ApplicationTestDataFactory.getCreateReservationTestCommand();

        StayDate stayDate = new StayDate(command.checkInDate(), command.checkOutDate());
        UUID roomId = UUID.randomUUID();
        List<UUID> roomIds = List.of(roomId);

        try (MockedStatic<Reservation> reservationStatic = mockStatic(Reservation.class)) {
            reservationStatic.when(() -> Reservation.createValidStayDate(command.checkInDate(), command.checkOutDate()))
                    .thenReturn(stayDate);

            when(roomManagementFacade.findAllRoomIdsForRoomType(command.roomTypeId()))
                    .thenReturn(roomIds);

            when(reservationAvailabilityService.findAvailableRoomToReserveForStayDate(roomIds, stayDate))
                    .thenThrow(new IllegalStateException("No available room"));

            assertThrows(IllegalStateException.class, () -> handler.handle(command));

            verify(roomManagementFacade).findAllRoomIdsForRoomType(command.roomTypeId());
            verify(reservationAvailabilityService).findAvailableRoomToReserveForStayDate(roomIds, stayDate);

            verifyNoInteractions(reservationCodeGenerationService);
            verifyNoInteractions(confirmationCodeGenerationService);
            verifyNoInteractions(reservationRepository);
        }
    }
}