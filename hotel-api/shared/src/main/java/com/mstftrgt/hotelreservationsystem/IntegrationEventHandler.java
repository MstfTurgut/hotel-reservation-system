package com.mstftrgt.hotelreservationsystem;

public interface IntegrationEventHandler<I extends IntegrationEvent> {

    void handle(I event);
}
