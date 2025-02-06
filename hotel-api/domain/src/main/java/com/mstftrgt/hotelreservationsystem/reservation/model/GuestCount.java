package com.mstftrgt.hotelreservationsystem.reservation.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class GuestCount {

    private int adultCount;
    private int childCount;
}
