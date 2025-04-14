package com.mstftrgt.hotelreservationsystem.readmodel;

import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import lombok.Builder;
import java.util.UUID;
import java.time.LocalDate;

@Builder
public record ReservationReadModel(
        UUID id,
        UUID userId,
        UUID roomId,
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

    public static ReservationReadModel from(Reservation reservation) {
        return ReservationReadModel
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
