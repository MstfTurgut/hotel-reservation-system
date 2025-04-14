package com.mstftrgt.hotelreservationsystem.contract;

import lombok.Builder;
import java.util.UUID;

import java.math.BigDecimal;

@Builder
public record RoomTypeContract(
        UUID id,
        String title,
        String description,
        BigDecimal priceForNight,
        int numberOfRooms,
        int adultCapacity,
        int childCapacity
) {}
