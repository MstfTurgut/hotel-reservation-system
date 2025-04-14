package com.mstftrgt.hotelreservationsystem;


import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.List;

public class AggregateRoot extends AbstractAggregateRoot<AggregateRoot> {

    public List<Object> getDomainEvents() {
        return (List<Object>) this.domainEvents();
    }

}
