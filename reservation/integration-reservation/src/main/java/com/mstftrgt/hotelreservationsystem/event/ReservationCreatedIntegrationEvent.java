package com.mstftrgt.hotelreservationsystem.event;

import com.mstftrgt.hotelreservationsystem.generic.integration.IntegrationEvent;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.util.UUID;

@With
@Builder
public record ReservationCreatedIntegrationEvent(
        BigDecimal totalPrice,
        UUID reservationId,
        CardDetails cardDetails
) implements IntegrationEvent {
}
