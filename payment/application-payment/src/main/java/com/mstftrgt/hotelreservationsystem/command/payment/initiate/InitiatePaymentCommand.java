package com.mstftrgt.hotelreservationsystem.command.payment.initiate;

import com.mstftrgt.hotelreservationsystem.cqrs.Command;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record InitiatePaymentCommand(
        UUID reservationId,
        BigDecimal paymentAmount,
        CardDetails cardDetails

) implements Command {}
