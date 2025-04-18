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
import java.time.LocalDateTime;
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
        UUID commonId = UUID.fromString("00000000-0000-0000-0000-000000000000");

        GuestSpecification guestSpec = new GuestSpecification(1, 1);

        StayDate stayDate = new StayDate(
                LocalDate.of(2030, 1, 1),
                LocalDate.of(2030, 1, 3)
        );

        CustomerDetails customerDetails = new CustomerDetails(
                "testFullName",
                "test@example.com",
                "1234567890"
        );

        return new Reservation(
                commonId,
                commonId,
                commonId,
                "testConfirmationCode",
                "testReservationCode",
                guestSpec,
                ReservationStatus.CONFIRMED,
                stayDate,
                customerDetails,
                LocalDateTime.now()
        );
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

    public static Reservation getTestReservationWith(StayDate stayDate, ReservationStatus status) {
        return getTestReservation().withStayDate(stayDate).withStatus(status);
    }

}
