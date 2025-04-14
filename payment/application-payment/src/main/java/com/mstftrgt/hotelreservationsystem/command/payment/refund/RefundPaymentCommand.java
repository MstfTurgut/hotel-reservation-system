package com.mstftrgt.hotelreservationsystem.command.payment.refund;

import com.mstftrgt.hotelreservationsystem.cqrs.Command;
import lombok.Builder;
import java.util.UUID;

@Builder
public record RefundPaymentCommand(UUID reservationId) implements Command {
}