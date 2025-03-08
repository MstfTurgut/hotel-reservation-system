package com.mstftrgt.hotelreservationsystem.reservation.service;


import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class ReservationAvailabilityService {

    private final ReservationRepository reservationRepository;


    public Long getAvailableRoomForReservation(List<Long> roomIds, LocalDate checkInDate, LocalDate checkOutDate) {
        return roomIds
                .stream()
                .filter(roomId -> isReservationAvailable(roomId, checkInDate, checkOutDate))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No available room found"));
    }


    public int getNumberOfAvailableRoomsForReservation(List<Long> roomIds, LocalDate checkInDate, LocalDate checkOutDate) {
        return (int)roomIds
                .stream()
                .filter(roomId -> isReservationAvailable(roomId, checkInDate, checkOutDate))
                .count();
    }

    private boolean isReservationAvailable(Long roomId, LocalDate requestedCheckIn, LocalDate requestedCheckOut) {

        List<Reservation> roomReservations =
                reservationRepository.findReservationsOfRoomByStayDate(
                        roomId,
                        new StayDate(requestedCheckIn, requestedCheckOut)
                );

        if (roomReservations.isEmpty()) {
            return true;
        }

        return roomReservations.stream()
                .noneMatch(existingReservation ->
                        isDateRangeOverlap(
                                requestedCheckIn,
                                requestedCheckOut,
                                existingReservation.getStayDate().getCheckInDate(),
                                existingReservation.getStayDate().getCheckOutDate()
                        )
                );
    }


    private boolean isDateRangeOverlap(LocalDate start1, LocalDate end1,
                                       LocalDate start2, LocalDate end2) {
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }

}
