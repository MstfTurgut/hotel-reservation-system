package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.IntegrationEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ReservationCancelledIntegrationEvent(
        UUID reservationId
) implements IntegrationEvent {
}
