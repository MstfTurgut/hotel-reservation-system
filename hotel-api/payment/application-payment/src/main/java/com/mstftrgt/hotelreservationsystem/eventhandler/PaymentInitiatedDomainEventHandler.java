package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.CommandDispatcher;
import com.mstftrgt.hotelreservationsystem.DomainEventHandler;
import com.mstftrgt.hotelreservationsystem.command.payment.processinhotel.ProcessInHotelPaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.processonline.ProcessOnlinePaymentCommand;
import com.mstftrgt.hotelreservationsystem.event.PaymentInitiatedDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentInitiatedDomainEventHandler implements DomainEventHandler<PaymentInitiatedDomainEvent> {

    private final CommandDispatcher commandDispatcher;

    @Override
    public void handle(PaymentInitiatedDomainEvent event) {

        if (event.cardDetails() == null) {
            ProcessInHotelPaymentCommand processInHotelPaymentCommand = ProcessInHotelPaymentCommand.builder()
                    .paymentId(event.paymentId())
                    .build();
            commandDispatcher.dispatch(processInHotelPaymentCommand);
        } else {
            ProcessOnlinePaymentCommand processOnlinePaymentCommand = ProcessOnlinePaymentCommand.builder()
                    .paymentId(event.paymentId())
                    .cardDetails(event.cardDetails())
                    .build();
            commandDispatcher.dispatch(processOnlinePaymentCommand);
        }
    }

}
