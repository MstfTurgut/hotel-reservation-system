package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.service.PriceCalculationService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceCalculationServiceTests {

    private final PriceCalculationService service = new PriceCalculationService();

    @Test
    void testCalculateTotalPrice_forThreeNights() {
        BigDecimal pricePerNight = new BigDecimal("100.00");
        StayDate stayDate = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 4))
                .build();

        BigDecimal totalPrice = service.calculateTotalPrice(pricePerNight, stayDate);

        assertEquals(new BigDecimal("300.00"), totalPrice);
    }

}
