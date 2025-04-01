package com.mstftrgt.hotelreservationsystem.reservation.model;

import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.reservation.dto.ReservationCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class GuestSpecification {

    int adultGuestCount;
    int childGuestCount;
}