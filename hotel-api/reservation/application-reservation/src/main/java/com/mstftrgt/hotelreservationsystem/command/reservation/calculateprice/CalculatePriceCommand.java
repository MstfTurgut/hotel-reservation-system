package com.mstftrgt.hotelreservationsystem.command.reservation.calculateprice;


import com.mstftrgt.hotelreservationsystem.Command;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class CalculatePriceCommand implements Command {
    private final Long reservationId;
    private final CardDetails cardDetails;
}


