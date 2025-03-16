package com.mstftrgt.hotelreservationsystem.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor
public class StayDate {

    LocalDate checkInDate;
    LocalDate checkOutDate;

    public boolean overlaps(StayDate other) {
        return !checkInDate.isAfter(other.checkOutDate) &&
                !other.checkInDate.isAfter(checkOutDate);
    }

    public boolean isInverse() {
        return checkInDate.isAfter(checkOutDate);
    }

    public boolean isPast() {
        return checkInDate.isBefore(LocalDate.now());
    }

}
