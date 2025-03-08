package com.mstftrgt.hotelreservationsystem.readmodel;

import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ReservationQueryReadModel(
        Long id,
        Long userId,
        Long roomId,
        String reservationCode,
        int adultGuestCount,
        int childGuestCount,
        String status,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        String customerFullName,
        String customerPhoneNumber,
        String customerEmailAddress

) {

    public static ReservationQueryReadModel from(Reservation reservation) {
        return ReservationQueryReadModel
                .builder()
                .id(reservation.getId())
                .userId(reservation.getUserId())
                .roomId(reservation.getUserId())
                .reservationCode(reservation.getReservationCode())
                .adultGuestCount(reservation.getGuestSpecification().getAdultGuestCount())
                .childGuestCount(reservation.getGuestSpecification().getChildGuestCount())
                .status(reservation.getStatus().toString())
                .checkInDate(reservation.getStayDate().getCheckInDate())
                .checkOutDate(reservation.getStayDate().getCheckOutDate())
                .customerFullName(reservation.getCustomerDetails().getFullName())
                .customerPhoneNumber(reservation.getCustomerDetails().getPhoneNumber())
                .customerEmailAddress(reservation.getCustomerDetails().getEmailAddress())
                .build();
    }
}
