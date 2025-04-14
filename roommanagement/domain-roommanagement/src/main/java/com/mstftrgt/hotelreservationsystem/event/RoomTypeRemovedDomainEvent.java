package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.DomainEvent;

import java.util.UUID;

public record RoomTypeRemovedDomainEvent(UUID roomTypeId) implements DomainEvent {
}
