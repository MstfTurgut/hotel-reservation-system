package com.mstftrgt.hotelreservationsystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
public abstract class DomainEventHandler<T extends DomainEvent> {

    @EventListener
    public void onEvent(T event) {
        log.info("Handling domain event: {}", event);
        handleEvent(event);
    }

    protected abstract void handleEvent(T event);
}