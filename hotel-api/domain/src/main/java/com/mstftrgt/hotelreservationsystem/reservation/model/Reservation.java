package com.mstftrgt.hotelreservationsystem.reservation.model;

import io.craftgate.modulith.messaging.api.model.AggregateRoot;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class Reservation extends AggregateRoot {

    private Long id;
    private Long userId;
    private Long roomId;
    private String confirmationCode;
    private GuestCount guestCount;
    private ReservationStatus reservationStatus;
    private StayDate stayDate;
    private CustomerDetails customerDetails;
}
