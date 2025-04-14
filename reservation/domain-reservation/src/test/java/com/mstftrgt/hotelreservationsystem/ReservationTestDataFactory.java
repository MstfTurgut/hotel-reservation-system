package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.reservation.dto.ReservationCreate;
import com.mstftrgt.hotelreservationsystem.reservation.model.CustomerDetails;
import com.mstftrgt.hotelreservationsystem.reservation.model.GuestSpecification;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.ReservationStatus;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ReservationTestDataFactory {


    public static ReservationCreate getTestReservationCreate() {
        return ReservationCreate.builder()
                .userId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .roomId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .adultGuestCount(1)
                .childGuestCount(1)
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 3))
                .customerFullName("testFullName")
                .customerEmailAddress("test@example.com")
                .customerPhoneNumber("1234567890")
                .confirmationCode("testConfirmationCode")
                .reservationCode("testReservationCode")
                .totalPrice(BigDecimal.TEN)
                .cardDetails(new CardDetails("testCardNumber", "testHolderName", "testExpiryDate", "testCvv"))
                .build();
    }

    public static Reservation getTestReservation() {
        return Reservation.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .userId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .roomId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .confirmationCode("testConfirmationCode")
                .reservationCode("testReservationCode")
                .guestSpecification(GuestSpecification.builder()
                        .adultGuestCount(1)
                        .childGuestCount(1)
                        .build())
                .status(ReservationStatus.CONFIRMED)
                .stayDate(StayDate.builder()
                        .checkInDate(LocalDate.of(2030, 1, 1))
                        .checkOutDate(LocalDate.of(2030, 1, 3))
                        .build())
                .customerDetails(CustomerDetails.builder()
                        .fullName("testFullName")
                        .emailAddress("test@example.com")
                        .phoneNumber("1234567890")
                        .build())
                .build();
    }

    public static Reservation getTestReservationWith(ReservationStatus status) {
        return getTestReservation().withStatus(status);
    }

    public static Reservation getTestReservationWith(String confirmationCode) {
        return getTestReservation().withConfirmationCode(confirmationCode);
    }

    public static Reservation getTestReservationWith(StayDate stayDate) {
        return getTestReservation().withStayDate(stayDate);
    }

}
