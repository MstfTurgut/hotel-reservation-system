package com.mstftrgt.hotelreservationsystem.reservation.model;

import com.mstftrgt.hotelreservationsystem.domain.AggregateRoot;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reservation implements AggregateRoot {

    private Long id;
    private Long userId;
    private Long roomId;
    private String confirmationCode;
    private GuestSpecification guestSpecification;
    private ReservationStatus status;
    private StayDate stayDate;
    private CustomerDetails customerDetails;
    private String reservationCode;

    public void confirmCustomer(String confirmationCode) {
        if(!confirmationCode.equals(this.confirmationCode)) {
            throw new IllegalArgumentException("Confirmation code is not valid");
        }
    }

    public void cancel() {
        if(status == ReservationStatus.CANCELLED) {
            throw new IllegalArgumentException("Reservation is already cancelled");
        }
        status = ReservationStatus.CANCELLED;
    }

    public void checkIn() {
        if(status == ReservationStatus.CHECKED_IN) {
            throw new IllegalArgumentException("Reservation is already checked in");
        }
        status = ReservationStatus.CHECKED_IN;
    }

    public void checkOut() {
        if(status == ReservationStatus.CHECKED_OUT) {
            throw new IllegalArgumentException("Reservation is already checked out");
        }
        status = ReservationStatus.CHECKED_OUT;
    }


}
