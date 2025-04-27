package com.mstftrgt.hotelreservationsystem.generic.domain;

import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.List;

@NoArgsConstructor
public class AggregateRoot extends AbstractAggregateRoot<AggregateRoot> {

    public List<Object> getDomainEvents() {
        return (List<Object>) this.domainEvents();
    }

    public void publishAllEventsAndClear(ApplicationEventPublisher publisher) {
        this.domainEvents().forEach(publisher::publishEvent);
        this.clearDomainEvents();
    }
}
