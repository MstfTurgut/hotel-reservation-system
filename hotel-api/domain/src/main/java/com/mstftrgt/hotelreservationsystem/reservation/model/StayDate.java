package com.mstftrgt.hotelreservationsystem.reservation.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class StayDate {

    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
