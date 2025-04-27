package com.mstftrgt.hotelreservationsystem.command.payment.processinhotel;

import com.mstftrgt.hotelreservationsystem.generic.application.Command;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProcessInHotelPaymentCommand(
        UUID reservationId,
        BigDecimal paymentAmount
) implements Command {
}
