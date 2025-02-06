package com.mstftrgt.hotelreservationsystem.reservation.port;

import com.mstftrgt.hotelreservationsystem.reservation.model.CustomerDetails;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationPort {

    Reservation save(Reservation reservation);

    List<Long> getUnavailableRoomIdsOfRoomType(Long roomTypeId, LocalDateTime checkInDate, LocalDateTime checkOutDate);

    int getNumberOfUnavailableRoomsOfRoomType(Long roomTypeId, LocalDateTime checkInDate, LocalDateTime checkOutDate);

    List<Reservation> findReservationsByCustomerInfo(String name, String surname, String phoneNumber);

    void notifyCustomer(CustomerDetails customerDetails);

    List<Reservation> findAllByUserId(Long userId);

    Reservation findById(Long reservationId);
}
