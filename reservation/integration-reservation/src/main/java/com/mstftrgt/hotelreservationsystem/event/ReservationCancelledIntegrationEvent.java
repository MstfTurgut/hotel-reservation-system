package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.generic.integration.IntegrationEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ReservationCancelledIntegrationEvent(
        UUID reservationId

) implements IntegrationEvent {}


