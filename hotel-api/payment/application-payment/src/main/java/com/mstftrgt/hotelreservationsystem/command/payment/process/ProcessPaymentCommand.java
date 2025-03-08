package com.mstftrgt.hotelreservationsystem.command.payment.process;

import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;

@Data
@Builder
public class ProcessPaymentCommand implements Command {
    private final BigDecimal amount;
    private final Long reservationId;
    private final CardDetails cardDetails;
}