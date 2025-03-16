package com.mstftrgt.hotelreservationsystem.facade;

import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RequiredArgsConstructor
public class ReservationFacadeImpl implements ReservationFacade {

    private final ReservationRepository reservationRepository;
    private final RoomManagementFacade roomManagementFacade;

    @Override
    public ReservationInfoContract findReservationById(UUID reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));

        RoomTypeContract roomType = roomManagementFacade.findRoomTypeByRoomId(reservation.getRoomId());

        return ReservationInfoContract.from(reservation, roomType.title());
    }
}
