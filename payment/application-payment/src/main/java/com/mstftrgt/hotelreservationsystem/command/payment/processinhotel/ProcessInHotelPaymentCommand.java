package com.mstftrgt.hotelreservationsystem.command.payment.processinhotel;

import com.mstftrgt.hotelreservationsystem.cqrs.Command;
import lombok.Builder;
import java.util.UUID;

@Builder
public record ProcessInHotelPaymentCommand(
    UUID paymentId
) implements Command {
}
