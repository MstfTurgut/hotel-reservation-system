package com.mstftrgt.hotelreservationsystem.command.reservation.create;


import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.reservation.dto.ReservationCreate;
import com.mstftrgt.hotelreservationsystem.reservation.model.CustomerDetails;
import com.mstftrgt.hotelreservationsystem.reservation.model.GuestSpecification;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CreateReservationCommand(
        UUID userId,
        UUID roomTypeId,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        int adultGuestCount,
        int childGuestCount,
        String fullName,
        String phoneNumber,
        String emailAddress,
        BigDecimal totalPrice,
        CardDetails cardDetails) implements Command {

    public ReservationCreate toReservationCreateWith(UUID roomId, StayDate requestedStay, String confirmationCode, String reservationCode) {
        return ReservationCreate.builder()
                .userId(userId)
                .roomId(roomId)
                .guestSpecification(GuestSpecification
                        .builder()
                        .adultGuestCount(adultGuestCount)
                        .childGuestCount(childGuestCount)
                        .build())
                .stayDate(requestedStay)
                .customerDetails(CustomerDetails
                        .builder()
                        .fullName(fullName)
                        .phoneNumber(phoneNumber)
                        .emailAddress(emailAddress)
                        .build())
                .confirmationCode(confirmationCode)
                .reservationCode(reservationCode)
                .totalPrice(totalPrice)
                .cardDetails(cardDetails)
                .build();
    }
}