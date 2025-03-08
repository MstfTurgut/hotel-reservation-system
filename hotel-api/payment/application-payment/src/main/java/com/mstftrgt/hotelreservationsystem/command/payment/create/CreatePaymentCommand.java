package com.mstftrgt.hotelreservationsystem.command.payment.create;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreatePaymentCommand implements Command {
    private final BigDecimal totalPrice;
    private final Long reservationId;
    private final Long transactionId;
}