package com.mstftrgt.hotelreservationsystem.reservation.repository.dto;

import com.mstftrgt.hotelreservationsystem.reservation.model.ReservationStatus;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.model.CustomerDetails;
import com.mstftrgt.hotelreservationsystem.reservation.model.GuestSpecification;
import lombok.Builder;

@Builder
public record ReservationCreate (
        Long roomId,
        GuestSpecification guestSpecification,
        ReservationStatus status,
        StayDate stayDate,
        CustomerDetails customerDetails,
        String confirmationCode,
        String reservationCode
) {
}
