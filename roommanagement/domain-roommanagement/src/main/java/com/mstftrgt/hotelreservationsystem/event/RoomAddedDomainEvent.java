package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.DomainEvent;
import java.util.UUID;

public record RoomAddedDomainEvent(UUID roomTypeId) implements DomainEvent {
}
