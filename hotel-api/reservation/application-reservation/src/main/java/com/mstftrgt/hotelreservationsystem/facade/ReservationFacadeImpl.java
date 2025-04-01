package com.mstftrgt.hotelreservationsystem.facade;

import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ReservationFacadeImpl implements ReservationFacade {

    private final ReservationRepository reservationRepository;
    private final RoomManagementFacade roomManagementFacade;

    @Override
    public Optional<ReservationInfoContract> findReservationById(UUID reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        if(optionalReservation.isEmpty()) return Optional.empty();

        Reservation reservation = optionalReservation.get();

        RoomTypeContract roomType = roomManagementFacade.findRoomTypeByRoomId(reservation.getRoomId());

        return Optional.of(buildReservationInfoContract(reservation, roomType));
    }

    private ReservationInfoContract buildReservationInfoContract(Reservation reservation, RoomTypeContract roomType) {
        return ReservationInfoContract.builder()
                .customerFullName(reservation.getCustomerDetails().getFullName())
                .customerPhoneNumber(reservation.getCustomerDetails().getPhoneNumber())
                .customerEmailAddress(reservation.getCustomerDetails().getEmailAddress())
                .reservationCode(reservation.getReservationCode())
                .confirmationCode(reservation.getConfirmationCode())
                .checkInDate(reservation.getStayDate().getCheckInDate())
                .checkOutDate(reservation.getStayDate().getCheckOutDate())
                .roomTypeTitle(roomType.title())
                .adultGuestCount(reservation.getGuestSpecification().getAdultGuestCount())
                .childGuestCount(reservation.getGuestSpecification().getChildGuestCount())
                .build();
    }
}
