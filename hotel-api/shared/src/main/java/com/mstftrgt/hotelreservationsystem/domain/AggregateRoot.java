package com.mstftrgt.hotelreservationsystem.domain;


import com.mstftrgt.hotelreservationsystem.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Collection;

@RequiredArgsConstructor
public class AggregateRoot extends AbstractAggregateRoot<AggregateRoot> {

    private final DomainEventPublisher domainEventPublisher;

    public void publishAllEvents() {
        Collection<Object> domainEvents = this.domainEvents();
        for (Object domainEvent : domainEvents) {
            domainEventPublisher.publish((DomainEvent) domainEvent);
        }
        this.clearDomainEvents();
    }


}
