package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;

public record RoomRemoved(long roomTypeId) implements DomainEvent { }
