package com.mstftrgt.hotelreservationsystem.command.reservation.create;

import com.mstftrgt.hotelreservationsystem.IdentityManagementFacade;
import com.mstftrgt.hotelreservationsystem.UserContract;
import com.mstftrgt.hotelreservationsystem.facade.RoomManagementFacade;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandHandler;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
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
public class CreateReservationCommandHandler implements CommandHandler<CreateReservationCommand, UUID> {

    private final RoomManagementFacade roomManagementFacade;
    private final ReservationRepository reservationRepository;
    private final IdentityManagementFacade identityManagementFacade;
    private final ReservationAvailabilityService reservationAvailabilityService;
    private final ReservationCodeGenerationService reservationCodeGenerationService;
    private final ConfirmationCodeGenerationService confirmationCodeGenerationService;


    @Override
    public UUID handle(CreateReservationCommand command) {
        StayDate requestedStay = Reservation.createValidStayDate(command.checkInDate(), command.checkOutDate());

        List<UUID> roomIdsForRoomType = roomManagementFacade.findAllRoomIdsForRoomType(command.roomTypeId());

        UUID roomIdToReserve = reservationAvailabilityService
                .findAvailableRoomToReserveForStayDate(roomIdsForRoomType, requestedStay);

        UserContract currentUser = identityManagementFacade.getCurrentUser();

        ReservationCreate reservationCreate = buildReservationCreate(
                        command,
                        currentUser.id(),
                        roomIdToReserve,
                        confirmationCodeGenerationService.generateConfirmationCode(),
                        reservationCodeGenerationService.generateReservationCode());

        Reservation reservation = Reservation.create(reservationCreate);

        reservationRepository.save(reservation);

        return reservation.getId();
    }

    private ReservationCreate buildReservationCreate(CreateReservationCommand command, UUID userId, UUID roomId, String confirmationCode, String reservationCode) {
        return ReservationCreate.builder()
                .userId(userId)
                .roomId(roomId)
                .adultGuestCount(command.adultGuestCount())
                .childGuestCount(command.childGuestCount())
                .checkInDate(command.checkInDate())
                .checkOutDate(command.checkOutDate())
                .customerFullName(command.fullName())
                .customerPhoneNumber(command.phoneNumber())
                .customerEmailAddress(command.emailAddress())
                .confirmationCode(confirmationCode)
                .reservationCode(reservationCode)
                .totalPrice(command.totalPrice())
                .cardDetails(command.cardDetails())
                .build();
    }

}
