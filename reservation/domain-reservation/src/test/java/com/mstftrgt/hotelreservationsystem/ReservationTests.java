package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.reservation.dto.ReservationCreate;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCancelledDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCreatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.exception.*;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.ReservationStatus;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReservationTests {

    @Test
    void shouldCreateReservation() {
        ReservationCreate testReservationCreate  = ReservationTestDataFactory.getTestReservationCreate();

        Reservation reservation = Reservation.create(testReservationCreate);

        assertNotNull(reservation.getId());
        assertEquals(testReservationCreate.userId(), reservation.getUserId());
        assertEquals(testReservationCreate.roomId(), reservation.getRoomId());
        assertEquals(testReservationCreate.adultGuestCount(), reservation.getGuestSpecification().getAdultGuestCount());
        assertEquals(testReservationCreate.childGuestCount(), reservation.getGuestSpecification().getChildGuestCount());
        assertEquals(testReservationCreate.checkInDate(), reservation.getStayDate().getCheckInDate());
        assertEquals(testReservationCreate.checkOutDate(), reservation.getStayDate().getCheckOutDate());
        assertEquals(testReservationCreate.customerFullName(), reservation.getCustomerDetails().getFullName());
        assertEquals(testReservationCreate.customerEmailAddress(), reservation.getCustomerDetails().getEmailAddress());
        assertEquals(testReservationCreate.customerPhoneNumber(), reservation.getCustomerDetails().getPhoneNumber());
        assertEquals(testReservationCreate.confirmationCode(), reservation.getConfirmationCode());
        assertEquals(testReservationCreate.reservationCode(), reservation.getReservationCode());
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());

        assertEquals(1, reservation.getDomainEvents().size());

        ReservationCreatedDomainEvent event = (ReservationCreatedDomainEvent) reservation.getDomainEvents().get(0);

        assertEquals(event.reservationId(), reservation.getId());
        assertEquals(event.totalPrice(), testReservationCreate.totalPrice());
        assertEquals(event.cardDetails(), testReservationCreate.cardDetails());
    }

    @Test
    void confirmCustomer_withValidConfirmationCode_shouldNotThrowException() {
        Reservation reservation = ReservationTestDataFactory.getTestReservationWith("validConfirmationCode");

        assertDoesNotThrow(() -> reservation.confirmCustomer("validConfirmationCode"));
    }

    @Test
    void confirmCustomer_withInvalidConfirmationCode_shouldThrowException() {
        Reservation reservation = ReservationTestDataFactory.getTestReservationWith("validConfirmationCode");

        assertThrows(ConfirmationCodeIsNotValidException.class,
                () -> reservation.confirmCustomer("invalidConfirmationCode"));
    }

    @Test
    void cancel_whenReservationAlreadyCancelled_shouldThrowException() {
        Reservation reservation = ReservationTestDataFactory.getTestReservationWith(ReservationStatus.CANCELLED);

        assertThrows(ReservationAlreadyCancelledException.class, reservation::cancel);
    }

    @Test
    void cancel_whenLastMinute_shouldThrowException() {
        StayDate stayDate = new StayDate(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));

        Reservation reservation = ReservationTestDataFactory.getTestReservationWith(stayDate);

        assertThrows(LastMinuteCancellationException.class, reservation::cancel);
    }

    @Test
    void cancel_whenValid_shouldCancelAndRegisterEvent() {
        StayDate stayDate = new StayDate(LocalDate.now().plusDays(10), LocalDate.now().plusDays(15));

        Reservation reservation = ReservationTestDataFactory.getTestReservationWith(stayDate, ReservationStatus.CONFIRMED);

        reservation.cancel();

        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());

        assertEquals(1, reservation.getDomainEvents().size());

        ReservationCancelledDomainEvent event = (ReservationCancelledDomainEvent) reservation.getDomainEvents().get(0);

        assertEquals(event.reservationId(), reservation.getId());
    }

    @Test
    void checkIn_whenAlreadyCheckedIn_shouldThrowException() {
        Reservation reservation = ReservationTestDataFactory.getTestReservationWith(ReservationStatus.CHECKED_IN);

        assertThrows(ReservationAlreadyCheckedInException.class, reservation::checkIn);
    }

    @Test
    void checkIn_whenValid_shouldChangeStatus() {
        Reservation reservation = ReservationTestDataFactory.getTestReservationWith(ReservationStatus.CONFIRMED);

        reservation.checkIn();

        assertEquals(ReservationStatus.CHECKED_IN, reservation.getStatus());
    }

    @Test
    void checkOut_whenAlreadyCheckedOut_shouldThrowException() {
        Reservation reservation = ReservationTestDataFactory.getTestReservationWith(ReservationStatus.CHECKED_OUT);

        assertThrows(ReservationAlreadyCheckedOutException.class, reservation::checkOut);
    }

    @Test
    void checkOut_whenValid_shouldChangeStatus() {
        Reservation reservation = ReservationTestDataFactory.getTestReservationWith(ReservationStatus.CHECKED_IN);

        reservation.checkOut();

        assertEquals(ReservationStatus.CHECKED_OUT, reservation.getStatus());
    }

    @Test
    void isNotCancelled_whenCancelled_shouldReturnFalse() {
        Reservation reservation = ReservationTestDataFactory.getTestReservationWith(ReservationStatus.CANCELLED);

        assertFalse(reservation.isNotCancelled());
    }

    @Test
    void isNotCancelled_whenNotCancelled_shouldReturnTrue() {
        Reservation reservation = ReservationTestDataFactory.getTestReservationWith(ReservationStatus.CONFIRMED);

        assertTrue(reservation.isNotCancelled());
    }

    @Test
    void createValidStayDate_withInverseDates_shouldThrowException() {
        LocalDate checkInDate = LocalDate.of(2030, 1, 10);
        LocalDate checkOutDate = LocalDate.of(2030, 1, 5);

        assertThrows(InvalidStayDateException.class, () ->
                Reservation.createValidStayDate(checkInDate, checkOutDate));
    }

    @Test
    void createValidStayDate_withPastDates_shouldThrowException() {
        LocalDate checkInDate = LocalDate.of(1900, 1, 5);
        LocalDate checkOutDate = LocalDate.of(1900, 1, 10);

        assertThrows(InvalidStayDateException.class, () ->
                Reservation.createValidStayDate(checkInDate, checkOutDate));
    }

    @Test
    void createValidStayDate_withValidDates_shouldReturnStayDate() {
        LocalDate checkInDate = LocalDate.of(2030, 1, 5);
        LocalDate checkOutDate = LocalDate.of(2030, 1, 10);

        StayDate stayDate = Reservation.createValidStayDate(checkInDate, checkOutDate);

        assertEquals(checkInDate, stayDate.getCheckInDate());
        assertEquals(checkOutDate, stayDate.getCheckOutDate());
    }

    @Test
    void createValidStayDate_withSameDates_shouldThrowException() {
        LocalDate checkInDate = LocalDate.of(2030, 1, 5);
        LocalDate checkOutDate = LocalDate.of(2030, 1, 5);

        assertThrows(InvalidStayDateException.class, () ->
                Reservation.createValidStayDate(checkInDate, checkOutDate));
    }
}

