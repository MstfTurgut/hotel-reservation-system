package com.mstftrgt.hotelreservationsystem.domain;


import com.mstftrgt.hotelreservationsystem.DomainEventPublisher;
import com.mstftrgt.hotelreservationsystem.DomainEventPublisherImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

public class AggregateRoot extends AbstractAggregateRoot<AggregateRoot> {

    private final DomainEventPublisher domainEventPublisher = new DomainEventPublisherImpl();

    public void publishAllEvents() {
        Collection<Object> domainEvents = this.domainEvents();
        for (Object domainEvent : domainEvents) {
            domainEventPublisher.publish((DomainEvent) domainEvent);
        }
        this.clearDomainEvents();
    }

    public List<Object> getDomainEvents() {
        return (List<Object>) this.domainEvents();
    }

}
