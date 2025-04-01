package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.command.reservation.cancel.CancelReservationCommand;
import com.mstftrgt.hotelreservationsystem.command.reservation.checkin.CheckInReservationCommand;
import com.mstftrgt.hotelreservationsystem.command.reservation.checkout.CheckOutReservationCommand;
import com.mstftrgt.hotelreservationsystem.command.reservation.create.CreateReservationCommand;
import com.mstftrgt.hotelreservationsystem.command.reservation.rollback.RollbackReservationCommand;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.eventhandler.ReservationCancelledDomainEventHandler;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.query.reservation.findavailabilitiesforroomtypes.FindReservationAvailabilitiesForSuitableRoomTypesQuery;
import com.mstftrgt.hotelreservationsystem.query.reservation.findforcustomer.FindReservationsOfCustomerQuery;
import com.mstftrgt.hotelreservationsystem.query.reservation.findforuser.FindReservationsOfUserQuery;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCancelledDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCreatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.model.CustomerDetails;
import com.mstftrgt.hotelreservationsystem.reservation.model.GuestSpecification;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.ReservationStatus;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ApplicationTestDataFactory {

    public static CreateReservationCommand getCreateReservationTestCommand() {
        return CreateReservationCommand.builder()
                .userId(UUID.randomUUID())
                .roomTypeId(UUID.randomUUID())
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 5))
                .adultGuestCount(2)
                .childGuestCount(1)
                .cardDetails(CardDetails.builder()
                        .cardNumber("0000000000000000")
                        .cardHolderName("testName")
                        .expiryDate("12/25")
                        .cvv("000")
                        .build())
                .emailAddress("test@example.com")
                .fullName("testFullName")
                .phoneNumber("1234567890")
                .totalPrice(BigDecimal.TEN)
                .build();
    }

    public static CancelReservationCommand getCancelReservationTestCommand() {
        return CancelReservationCommand.builder()
                .reservationId(UUID.randomUUID())
                .confirmationCode("testConfirmationCode")
                .build();
    }

    public static CheckInReservationCommand getCheckInReservationTestCommand() {
        return CheckInReservationCommand.builder()
                .reservationId(UUID.randomUUID())
                .confirmationCode("testConfirmationCode")
                .build();
    }

    public static CheckOutReservationCommand getCheckOutReservationTestCommand() {
        return CheckOutReservationCommand.builder()
                .reservationId(UUID.randomUUID())
                .build();
    }

    public static RollbackReservationCommand getRollbackReservationTestCommand() {
        return RollbackReservationCommand.builder()
                .reservationId(UUID.randomUUID())
                .build();
    }

    public static FindReservationsOfCustomerQuery getFindReservationsOfCustomerTestQuery() {
        return FindReservationsOfCustomerQuery.builder()
                .fullName("testFullName")
                .phoneNumber("1234567890")
                .build();
    }

    public static FindReservationsOfUserQuery getFindReservationsOfUserTestQuery() {
        return FindReservationsOfUserQuery.builder()
                .userId(UUID.randomUUID())
                .build();
    }

    public static FindReservationAvailabilitiesForSuitableRoomTypesQuery getFindReservationAvailabilitiesForSuitableRoomTypesTestQuery() {
        return FindReservationAvailabilitiesForSuitableRoomTypesQuery.builder()
                .adultGuestCount(2)
                .childGuestCount(1)
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 5))
                .build();
    }

    public static List<Reservation> getTestReservationList() {
        Reservation reservation1 = Reservation.builder()
                .id(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .roomId(UUID.randomUUID())
                .confirmationCode("testConfirmationCode")
                .reservationCode("testReservationCode")
                .guestSpecification(GuestSpecification.builder()
                        .adultGuestCount(2)
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

        Reservation reservation2 = Reservation.builder()
                .id(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .roomId(UUID.randomUUID())
                .confirmationCode("testConfirmationCode")
                .reservationCode("testReservationCode")
                .guestSpecification(GuestSpecification.builder()
                        .adultGuestCount(2)
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

        return List.of(reservation1, reservation2);
    }


    public static ReservationCreatedDomainEvent getReservationCreatedDomainEvent() {
        return ReservationCreatedDomainEvent.builder()
                .reservationId(UUID.randomUUID())
                .totalPrice(BigDecimal.TEN)
                .cardDetails(CardDetails.builder()
                        .cardNumber("0000000000000000")
                        .cardHolderName("testName")
                        .expiryDate("12/25")
                        .cvv("000")
                        .build())
                .build();
    }

    public static ReservationCancelledDomainEvent getReservationCancelledDomainEvent() {
        return ReservationCancelledDomainEvent.builder()
                .reservationId(UUID.randomUUID())
                .build();
    }

    public static PaymentFailedIntegrationEvent getPaymentFailedIntegrationEvent() {
        return PaymentFailedIntegrationEvent.builder()
                .reservationId(UUID.randomUUID())
                .paymentAmount(BigDecimal.TEN)
                .build();
    }
}
