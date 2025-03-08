package com.mstftrgt.hotelreservationsystem.reservation.event;


import com.mstftrgt.hotelreservationsystem.domain.DomainEvent;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;

import java.math.BigDecimal;

public record PriceCalculated (
    BigDecimal totalPrice,
    Long reservationId,
    CardDetails cardDetails
) implements DomainEvent {}
