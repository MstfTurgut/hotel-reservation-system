package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.command.payment.processinhotel.ProcessInHotelPaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.processonline.ProcessOnlinePaymentCommand;
import com.mstftrgt.hotelreservationsystem.event.ReservationCreatedIntegrationEvent;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationCreatedIntegrationEventHandler {

    private final CommandBus commandBus;

    @EventListener
    public void handle(ReservationCreatedIntegrationEvent event) {
        log.info("Handling integration event {}" , event);

        if (event.cardDetails() == null) {
            ProcessInHotelPaymentCommand processInHotelPaymentCommand = ProcessInHotelPaymentCommand.builder()
                    .reservationId(event.reservationId())
                    .paymentAmount(event.totalPrice())
                    .build();
            commandBus.dispatch(processInHotelPaymentCommand);
        } else {
            ProcessOnlinePaymentCommand processOnlinePaymentCommand = ProcessOnlinePaymentCommand.builder()
                    .reservationId(event.reservationId())
                    .paymentAmount(event.totalPrice())
                    .cardDetails(event.cardDetails())
                    .build();
            commandBus.dispatch(processOnlinePaymentCommand);
        }
    }
}
