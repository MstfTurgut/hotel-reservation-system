package com.mstftrgt.hotelreservationsystem.facade;

import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationFacadeImpl implements ReservationFacade {

    private final ReservationRepository reservationRepository;
    private final RoomManagementFacade roomManagementFacade;

    @Override
    public ReservationInfoContract findReservationById(long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        RoomTypeContract roomType = roomManagementFacade.findRoomTypeByRoomId(reservation.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        return ReservationInfoContract.from(reservation, roomType.title());

    }
}
