package com.mstftrgt.hotelreservationsystem.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class StayDate {

    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
}
