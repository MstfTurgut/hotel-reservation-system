package com.mstftrgt.hotelreservationsystem.reservation.repository;


import com.mstftrgt.hotelreservationsystem.domain.Repository;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.dto.ReservationCreate;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends Repository {

    Optional<Reservation> findById(UUID reservationId);

    List<Reservation> findReservationsOfRoomByStayDate(UUID roomId, StayDate stayDate);

    void save(Reservation reservation);

    void deleteById(UUID reservationId);

    List<Reservation> findAllByCustomerFullNameAndPhoneNumber(String fullName, String phoneNumber);

    List<Reservation> findAllByUserId(UUID userId);
}
