package com.mstftrgt.hotelreservationsystem.reservation.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class GuestSpecification {

    int adultGuestCount;
    int childGuestCount;
}
