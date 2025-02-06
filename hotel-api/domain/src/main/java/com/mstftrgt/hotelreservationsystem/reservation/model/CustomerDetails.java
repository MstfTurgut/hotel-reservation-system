package com.mstftrgt.hotelreservationsystem.reservation.model;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class CustomerDetails {

    private String name;
    private String surname;
    private String phoneNumber;
    private String emailAddress;
}
