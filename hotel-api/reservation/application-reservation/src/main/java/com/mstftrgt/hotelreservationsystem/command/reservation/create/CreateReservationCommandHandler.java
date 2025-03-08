package com.mstftrgt.hotelreservationsystem.command.reservation.create;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.DomainEventPublisher;
import com.mstftrgt.hotelreservationsystem.facade.RoomManagementFacade;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCreated;
import com.mstftrgt.hotelreservationsystem.reservation.model.CustomerDetails;
import com.mstftrgt.hotelreservationsystem.reservation.model.GuestSpecification;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.ReservationStatus;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.repository.dto.ReservationCreate;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import com.mstftrgt.hotelreservationsystem.reservation.service.ConfirmationCodeGenerationService;
import com.mstftrgt.hotelreservationsystem.reservation.service.ReservationAvailabilityService;
import com.mstftrgt.hotelreservationsystem.reservation.service.ReservationCodeGenerationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateReservationCommandHandler implements CommandHandler<CreateReservationCommand> {

    private final DomainEventPublisher eventPublisher;
    private final RoomManagementFacade roomManagementFacade;
    private final ReservationRepository reservationRepository;
    private final ReservationAvailabilityService reservationAvailabilityService;
    private final ReservationCodeGenerationService reservationCodeGenerationService;
    private final ConfirmationCodeGenerationService confirmationCodeGenerationService;


    @Override
    public void handle(CreateReservationCommand command) {

        // TODO validation

        List<Long> roomIdsForRoomType = getRoomIdsForRoomType(command.getRoomTypeId());

        Long roomIdToReserve = reservationAvailabilityService
                .getAvailableRoomForReservation(roomIdsForRoomType, command.getCheckInDate(), command.getCheckOutDate());

        ReservationCreate reservationCreate = buildReservationCreate(
                command,
                roomIdToReserve,
                reservationCodeGenerationService.generateReservationCode(),
                confirmationCodeGenerationService.generateConfirmationCode());

        Reservation reservation = reservationRepository.save(reservationCreate);

        eventPublisher.publish(new ReservationCreated(
                reservation.getId(),
                command.getCardDetails()
        ));
    }

    private List<Long> getRoomIdsForRoomType(long roomTypeId) {
        List<Long> roomIdsForRoomType = roomManagementFacade.findAllRoomIdsForRoomType(roomTypeId);

        if(roomIdsForRoomType.isEmpty()) {
            throw new IllegalArgumentException("No available room found");
        }

        return roomIdsForRoomType;
    }

    private ReservationCreate buildReservationCreate(CreateReservationCommand command, Long roomIdToReserve, String reservationCode, String confirmationCode) {
        return ReservationCreate.builder()
                .roomId(roomIdToReserve)
                .stayDate(StayDate.builder()
                        .checkInDate(command.getCheckInDate())
                        .checkOutDate(command.getCheckOutDate())
                        .build())
                .guestSpecification(GuestSpecification.builder()
                        .adultGuestCount(command.getAdultGuestCount())
                        .childGuestCount(command.getChildGuestCount())
                        .build())
                .customerDetails(CustomerDetails.builder()
                        .fullName(command.getFullName())
                        .phoneNumber(command.getPhoneNumber())
                        .emailAddress(command.getEmailAddress())
                        .build())
                .status(ReservationStatus.WAITING)
                .reservationCode(reservationCode)
                .confirmationCode(confirmationCode)
                .build();
    }
}
