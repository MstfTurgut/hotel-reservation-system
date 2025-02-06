package com.mstftrgt.hotelreservationsystem.reservation.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class CardDetails {

    private String cardNumber;
    private String cvc;
    private String expiryMonth;
    private String expiryYear;
}
