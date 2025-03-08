package com.mstftrgt.hotelreservationsystem.reservation.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GuestSpecification {

    private int adultGuestCount;
    private int childGuestCount;

}
