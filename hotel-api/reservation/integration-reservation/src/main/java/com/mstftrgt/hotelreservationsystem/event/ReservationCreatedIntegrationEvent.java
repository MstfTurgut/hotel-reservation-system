package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.IntegrationEvent;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ReservationCreatedIntegrationEvent(
        BigDecimal totalPrice,
        UUID reservationId,
        CardDetails cardDetails
) implements IntegrationEvent {
}
