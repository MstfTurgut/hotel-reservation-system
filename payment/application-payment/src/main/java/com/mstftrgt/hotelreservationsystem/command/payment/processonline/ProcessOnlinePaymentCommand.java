package com.mstftrgt.hotelreservationsystem.command.payment.processonline;

import com.mstftrgt.hotelreservationsystem.generic.application.Command;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProcessOnlinePaymentCommand(
        UUID reservationId,
        BigDecimal paymentAmount,
        CardDetails cardDetails
) implements Command {
}
