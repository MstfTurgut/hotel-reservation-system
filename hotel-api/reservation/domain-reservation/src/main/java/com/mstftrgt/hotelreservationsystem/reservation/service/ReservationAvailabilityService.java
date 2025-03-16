package com.mstftrgt.hotelreservationsystem.reservation.service;


import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotAvailableException;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import java.util.List;

@RequiredArgsConstructor
public class ReservationAvailabilityService {

    private final ReservationRepository reservationRepository;


    public UUID getAvailableRoomForReservation(List<UUID> roomIds, StayDate requestedStay) {
        return roomIds
                .stream()
                .filter(roomId -> isReservationAvailable(roomId, requestedStay))
                .findFirst()
                .orElseThrow(ReservationNotAvailableException::new);
    }


    public int getNumberOfAvailableRoomsForReservation(List<UUID> roomIds, StayDate requestedStay) {
        return (int)roomIds
                .stream()
                .filter(roomId -> isReservationAvailable(roomId, requestedStay))
                .count();
    }

    private boolean isReservationAvailable(UUID roomId, StayDate requestedStay) {
        List<Reservation> roomReservations =
                reservationRepository.findReservationsOfRoomByStayDate(roomId, requestedStay);

        if (roomReservations.isEmpty()) {
            return true;
        }

        return roomReservations.stream()
                .noneMatch(existingReservation ->
                        existingReservation.isNotCancelled()
                        &&
                        existingReservation.getStayDate().overlaps(requestedStay)
                );
    }

}
