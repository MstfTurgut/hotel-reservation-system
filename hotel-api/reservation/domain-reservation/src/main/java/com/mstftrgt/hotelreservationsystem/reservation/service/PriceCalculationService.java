package com.mstftrgt.hotelreservationsystem.reservation.service;

import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;

import java.math.BigDecimal;

public class PriceCalculationService {

    public BigDecimal calculateTotalPrice(BigDecimal priceForNight, StayDate stayDate) {
        return priceForNight.multiply(BigDecimal.valueOf(stayDate.getCheckInDate().until(stayDate.getCheckOutDate()).getDays()));
    }
}
