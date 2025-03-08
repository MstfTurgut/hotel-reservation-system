package com.mstftrgt.hotelreservationsystem.command.payment.refund;

import com.mstftrgt.hotelreservationsystem.Command;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefundPaymentCommand implements Command {
    private final Long reservationId;
}