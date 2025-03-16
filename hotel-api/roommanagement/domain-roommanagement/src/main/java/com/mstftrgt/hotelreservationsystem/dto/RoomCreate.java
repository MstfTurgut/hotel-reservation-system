package com.mstftrgt.hotelreservationsystem.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record RoomCreate(
   UUID roomTypeId,
   String roomNumber
) {}
