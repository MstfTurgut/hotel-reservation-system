package com.mstftrgt.hotelreservationsystem.integration.controller;

import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CreateInHotelReservationRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CreateOnlineReservationRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ControllerTestDataFactory {

    public static CreateOnlineReservationRequest getTestCreateOnlineReservationRequest() {
        return new CreateOnlineReservationRequest(
                UUID.randomUUID(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                2,
                0,
                "John Doe",
                "123456789",
                "john.doe@example.com",
                BigDecimal.valueOf(300),
                new CardDetails("4111111111111111", "John Doe", "12/30", "123")
        );
    }

    public static CreateInHotelReservationRequest getTestCreateInHotelReservationRequest() {
        return new CreateInHotelReservationRequest(
                UUID.randomUUID(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                2,
                0,
                "John Doe",
                "123456789",
                "john.doe@example.com",
                BigDecimal.valueOf(300)
        );
    }



}
