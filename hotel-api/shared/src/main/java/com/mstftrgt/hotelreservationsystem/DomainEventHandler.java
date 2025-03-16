package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;

public interface DomainEventHandler<D extends DomainEvent> {

    void handle(D event);
}
