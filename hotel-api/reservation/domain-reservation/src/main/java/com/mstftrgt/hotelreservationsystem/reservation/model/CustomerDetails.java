package com.mstftrgt.hotelreservationsystem.reservation.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDetails {

    private final String fullName;
    private final String phoneNumber;
    private final String emailAddress;
}
