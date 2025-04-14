package com.mstftrgt.hotelreservationsystem;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IntegrationEventPublisher  {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(IntegrationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
