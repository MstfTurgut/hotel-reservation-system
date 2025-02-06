package com.mstftrgt.hotelreservationsystem.roomtype.model;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class GuestCapacity {

    private int adultCapacity;
    private int childCapacity;
}
