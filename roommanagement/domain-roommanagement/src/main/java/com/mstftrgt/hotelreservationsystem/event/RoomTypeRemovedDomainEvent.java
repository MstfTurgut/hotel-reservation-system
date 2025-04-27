package com.mstftrgt.hotelreservationsystem.event;


import com.mstftrgt.hotelreservationsystem.generic.domain.DomainEvent;

import java.util.UUID;

public record RoomTypeRemovedDomainEvent(UUID roomTypeId) implements DomainEvent {
}
