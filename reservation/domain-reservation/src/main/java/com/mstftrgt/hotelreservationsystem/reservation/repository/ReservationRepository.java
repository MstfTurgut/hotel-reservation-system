package com.mstftrgt.hotelreservationsystem.reservation.repository;


import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    Optional<Reservation> findById(UUID reservationId);

    List<Reservation> findReservationsOfRoom(UUID roomId);

    void save(Reservation reservation);

    void delete(Reservation reservation);

    List<Reservation> findAllByCustomerFullNameAndPhoneNumber(String fullName, String phoneNumber);

    List<Reservation> findAllByUserId(UUID userId);
}
