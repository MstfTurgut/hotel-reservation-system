package com.mstftrgt.hotelreservationsystem.reservation.model;

import com.mstftrgt.hotelreservationsystem.generic.domain.AggregateRoot;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCancelledDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.dto.ReservationCreate;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCreatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ConfirmationCodeIsNotValidException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.LastMinuteCancellationException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCancelledException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCheckedInException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCheckedOutException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.InvalidStayDateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@With
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Reservation extends AggregateRoot {

    private UUID id;
    private UUID userId;
    private UUID roomId;
    private String confirmationCode;
    private String reservationCode;
    private GuestSpecification guestSpecification;
    private ReservationStatus status;
    private StayDate stayDate;
    private CustomerDetails customerDetails;
    private LocalDateTime createdAt;

    public static Reservation create(ReservationCreate reservationCreate) {

        GuestSpecification guestSpecification = GuestSpecification.builder()
                .adultGuestCount(reservationCreate.adultGuestCount())
                .childGuestCount(reservationCreate.childGuestCount())
                .build();

        StayDate stayDate = createValidStayDate(reservationCreate.checkInDate(), reservationCreate.checkOutDate());

        CustomerDetails customerDetails = CustomerDetails.builder()
                .fullName(reservationCreate.customerFullName())
                .emailAddress(reservationCreate.customerEmailAddress())
                .phoneNumber(reservationCreate.customerPhoneNumber())
                .build();

        Reservation newReservation = new Reservation(
                UUID.randomUUID(),
                reservationCreate.userId(),
                reservationCreate.roomId(),
                reservationCreate.confirmationCode(),
                reservationCreate.reservationCode(),
                guestSpecification,
                ReservationStatus.CONFIRMED,
                stayDate,
                customerDetails,
                LocalDateTime.now()
        );

        newReservation.registerEvent(
                new ReservationCreatedDomainEvent(
                        newReservation.getId(),
                        reservationCreate.totalPrice(),
                        reservationCreate.cardDetails()));

        return newReservation;
    }

    public void confirmCustomer(String confirmationCode) {
        if(!confirmationCode.equals(this.confirmationCode)) {
            throw new ConfirmationCodeIsNotValidException();
        }
    }

    public void cancel() {
        if(status == ReservationStatus.CANCELLED) {
            throw new ReservationAlreadyCancelledException();
        }

        if (stayDate.getCheckInDate().isBefore(LocalDate.now().plusDays(2))) {
            throw new LastMinuteCancellationException();
        }

        status = ReservationStatus.CANCELLED;

        this.registerEvent(new ReservationCancelledDomainEvent(id));
    }

    public void checkIn() {
        if(status == ReservationStatus.CHECKED_IN) {
            throw new ReservationAlreadyCheckedInException();
        }
        status = ReservationStatus.CHECKED_IN;
    }

    public void checkOut() {
        if(status == ReservationStatus.CHECKED_OUT) {
            throw new ReservationAlreadyCheckedOutException();
        }
        status = ReservationStatus.CHECKED_OUT;
    }

    public boolean isNotCancelled() {
        return ReservationStatus.CANCELLED != status;
    }

    public static StayDate createValidStayDate(LocalDate checkInDate, LocalDate checkOutDate) {
        StayDate stayDate = new StayDate(checkInDate, checkOutDate);

        if(stayDate.isInverse()) {
            throw new InvalidStayDateException("check-in must be before check-out");
        }

        if(stayDate.isPast()) {
            throw new InvalidStayDateException("stay date cannot be in past");
        }

        if(stayDate.hasInvalidLength()) {
            throw new InvalidStayDateException("stay date must have length of at least one");
        }

        return stayDate;
    }
}
