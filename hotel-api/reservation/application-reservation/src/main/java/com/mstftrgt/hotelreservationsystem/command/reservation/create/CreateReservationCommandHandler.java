package com.mstftrgt.hotelreservationsystem.command.reservation.create;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.facade.RoomManagementFacade;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.dto.ReservationCreate;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import com.mstftrgt.hotelreservationsystem.reservation.service.ConfirmationCodeGenerationService;
import com.mstftrgt.hotelreservationsystem.reservation.service.ReservationAvailabilityService;
import com.mstftrgt.hotelreservationsystem.reservation.service.ReservationCodeGenerationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateReservationCommandHandler implements CommandHandler<CreateReservationCommand> {

    private final RoomManagementFacade roomManagementFacade;
    private final ReservationRepository reservationRepository;
    private final ReservationAvailabilityService reservationAvailabilityService;
    private final ReservationCodeGenerationService reservationCodeGenerationService;
    private final ConfirmationCodeGenerationService confirmationCodeGenerationService;


    @Override
    public void handle(CreateReservationCommand command) {

        StayDate requestedStay = Reservation.createValidStayDate(command.checkInDate(), command.checkOutDate());

        List<UUID> roomIdsForRoomType = roomManagementFacade.findAllRoomIdsForRoomType(command.roomTypeId());

        UUID roomIdToReserve = reservationAvailabilityService
                .getAvailableRoomForReservation(roomIdsForRoomType, requestedStay);

        ReservationCreate reservationCreate = command.toReservationCreateWith(
                        roomIdToReserve,
                        requestedStay,
                        confirmationCodeGenerationService.generateConfirmationCode(),
                        reservationCodeGenerationService.generateReservationCode());

        Reservation reservation = Reservation.create(reservationCreate);

        reservationRepository.save(reservation);
    }
}
