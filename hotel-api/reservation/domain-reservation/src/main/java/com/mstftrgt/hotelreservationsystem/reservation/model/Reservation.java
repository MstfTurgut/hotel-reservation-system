package com.mstftrgt.hotelreservationsystem.reservation.model;

import com.mstftrgt.hotelreservationsystem.domain.AggregateRoot;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCancelledDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.dto.ReservationCreate;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCreatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ConfirmationCodeIsNotValidException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.LastMinuteCancellationException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCancelledException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCheckedInException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationAlreadyCheckedOutException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.StayDateInPastException;
import com.mstftrgt.hotelreservationsystem.reservation.exception.InverseStayDateException;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
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

    public static Reservation create(ReservationCreate reservationCreate) {
        Reservation newReservation = Reservation.builder()
                .id(UUID.randomUUID())
                .userId(reservationCreate.userId())
                .roomId(reservationCreate.roomId())
                .confirmationCode(reservationCreate.confirmationCode())
                .reservationCode(reservationCreate.reservationCode())
                .guestSpecification(reservationCreate.guestSpecification())
                .status(ReservationStatus.CONFIRMED)
                .stayDate(reservationCreate.stayDate())
                .customerDetails(reservationCreate.customerDetails())
                .build();

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
            throw new InverseStayDateException();
        }

        if(stayDate.isPast()) {
            throw new StayDateInPastException();
        }

        return stayDate;
    }
}
