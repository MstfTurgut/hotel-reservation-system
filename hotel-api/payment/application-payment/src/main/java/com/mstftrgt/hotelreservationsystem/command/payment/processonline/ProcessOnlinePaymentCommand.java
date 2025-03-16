package com.mstftrgt.hotelreservationsystem.command.payment.processonline;

import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Builder;
import java.util.UUID;

@Builder
public record ProcessOnlinePaymentCommand(
        UUID paymentId,
        CardDetails cardDetails
) implements Command {
}
