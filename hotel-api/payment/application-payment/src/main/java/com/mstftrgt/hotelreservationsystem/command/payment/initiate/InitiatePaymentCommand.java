package com.mstftrgt.hotelreservationsystem.command.payment.initiate;

import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.dto.PaymentCreate;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record InitiatePaymentCommand(
        UUID reservationId,
        BigDecimal paymentAmount,
        CardDetails cardDetails
) implements Command {

    public PaymentCreate toPaymentCreate() {
        return PaymentCreate.builder()
                .reservationId(reservationId)
                .amount(paymentAmount)
                .build();
    }
}
