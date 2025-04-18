package com.mstftrgt.hotelreservationsystem;


import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.List;

@NoArgsConstructor
public class AggregateRoot extends AbstractAggregateRoot<AggregateRoot> {

    public List<Object> getDomainEvents() {
        return (List<Object>) this.domainEvents();
    }

}
