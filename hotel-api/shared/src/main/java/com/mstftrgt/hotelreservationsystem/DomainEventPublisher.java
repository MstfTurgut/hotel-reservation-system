package com.mstftrgt.hotelreservationsystem;


import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
