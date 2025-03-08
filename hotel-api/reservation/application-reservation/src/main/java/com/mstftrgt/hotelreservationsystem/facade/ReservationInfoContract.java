package com.mstftrgt.hotelreservationsystem.facade;

import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ReservationInfoContract(
        String customerFullName,

        String customerPhoneNumber,

        String customerEmailAddress,

        String reservationCode,
        String confirmationCode,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        String roomTypeTitle,
        int adultGuestCount,
        int childGuestCount
) {

    public static ReservationInfoContract from(Reservation reservation, String roomTypeTitle) {

        return ReservationInfoContract.builder()
                .customerFullName(reservation.getCustomerDetails().getFullName())
                .customerPhoneNumber(reservation.getCustomerDetails().getPhoneNumber())
                .customerEmailAddress(reservation.getCustomerDetails().getEmailAddress())
                .reservationCode(reservation.getReservationCode())
                .confirmationCode(reservation.getConfirmationCode())
                .checkInDate(reservation.getStayDate().getCheckInDate())
                .checkOutDate(reservation.getStayDate().getCheckOutDate())
                .roomTypeTitle(roomTypeTitle)
                .adultGuestCount(reservation.getGuestSpecification().getAdultGuestCount())
                .childGuestCount(reservation.getGuestSpecification().getChildGuestCount())
                .build();
    }


}
