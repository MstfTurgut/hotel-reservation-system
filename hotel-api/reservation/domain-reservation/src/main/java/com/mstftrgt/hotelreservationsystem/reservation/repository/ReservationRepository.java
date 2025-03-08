package com.mstftrgt.hotelreservationsystem.reservation.repository;


import com.mstftrgt.hotelreservationsystem.domain.Repository;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.repository.dto.ReservationCreate;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends Repository {

    Optional<Reservation> findById(long reservationId);

    void update(Reservation reservation);

    List<Reservation> findReservationsOfRoomByStayDate(long roomId, StayDate stayDate);

    Reservation save(ReservationCreate reservationCreate);

    void deleteById(long reservationId);

    List<Reservation> findAllByCustomerFullNameAndPhoneNumber(String fullName, String phoneNumber);

    List<Reservation> findAllByUserId(long userId);
}
