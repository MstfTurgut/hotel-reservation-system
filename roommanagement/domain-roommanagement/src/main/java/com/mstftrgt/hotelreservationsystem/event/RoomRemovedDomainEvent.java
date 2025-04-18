package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.DomainEvent;
import java.util.UUID;

public record RoomRemovedDomainEvent(UUID roomTypeId) implements DomainEvent { }
