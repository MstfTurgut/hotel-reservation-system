package com.mstftrgt.hotelreservationsystem.repository.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RoomTypeCreate(
        String title,
        String description,
        BigDecimal priceForNight,
        int adultCapacity,
        int childCapacity
) {}
