package com.mstftrgt.hotelreservationsystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
public abstract class IntegrationEventHandler<I extends IntegrationEvent> {

    @EventListener
    void on(I event) {
        log.info("Handling integration event: {}", event);
        handle(event);
    }

    protected abstract void handle(I event);
}
