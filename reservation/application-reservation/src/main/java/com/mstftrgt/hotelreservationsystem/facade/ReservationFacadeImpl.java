package com.mstftrgt.hotelreservationsystem.facade;

import com.mstftrgt.hotelreservationsystem.contract.ReservationInfoContract;
import com.mstftrgt.hotelreservationsystem.contract.RoomTypeContract;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationFacadeImpl implements ReservationFacade {

    private final ReservationRepository reservationRepository;
    private final RoomManagementFacade roomManagementFacade;

    @Override
    public ReservationInfoContract findReservationById(UUID reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));

        RoomTypeContract roomType = roomManagementFacade.findRoomTypeByRoomId(reservation.getRoomId());

        return buildReservationInfoContract(reservation, roomType);
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
