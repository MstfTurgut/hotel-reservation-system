package com.mstftrgt.hotelreservationsystem;


public interface IntegrationEventPublisher {
    void publish(IntegrationEvent event);
}
