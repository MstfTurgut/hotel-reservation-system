package com.mstftrgt.hotelreservationsystem.reservation.service;

import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PriceCalculationService {

    public BigDecimal calculateTotalPrice(BigDecimal priceForNight, StayDate stayDate) {
        return priceForNight.multiply(BigDecimal.valueOf(stayDate.getCheckInDate().until(stayDate.getCheckOutDate()).getDays()));
    }
}
