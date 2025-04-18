package com.mstftrgt.hotelreservationsystem.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CustomerDetails {

    String fullName;
    String phoneNumber;
    String emailAddress;
}
