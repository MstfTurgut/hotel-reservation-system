package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;

public record RoomAdded(long roomTypeId) implements DomainEvent {
}
