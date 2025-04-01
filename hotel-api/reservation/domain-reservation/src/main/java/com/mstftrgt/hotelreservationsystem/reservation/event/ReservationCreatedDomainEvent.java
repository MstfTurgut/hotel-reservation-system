package com.mstftrgt.hotelreservationsystem.reservation.event;


import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Builder;

import java.util.UUID;
import java.math.BigDecimal;

@Builder
public record ReservationCreatedDomainEvent(

        UUID reservationId,
        BigDecimal totalPrice,
        CardDetails cardDetails) implements DomainEvent {
}