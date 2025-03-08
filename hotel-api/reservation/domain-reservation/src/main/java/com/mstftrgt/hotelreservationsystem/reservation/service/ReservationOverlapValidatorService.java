package com.mstftrgt.hotelreservationsystem.reservation.service;


import com.mstftrgt.hotelreservationsystem.domain.DomainService;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class ReservationOverlapValidatorService implements DomainService {

    private final ReservationRepository reservationRepository;


    public boolean isReservationAvailable(Long roomId, LocalDate requestedCheckIn, LocalDate requestedCheckOut) {

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
