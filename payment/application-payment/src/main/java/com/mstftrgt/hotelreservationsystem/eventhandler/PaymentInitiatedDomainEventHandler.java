package com.mstftrgt.hotelreservationsystem.eventhandler;

import com.mstftrgt.hotelreservationsystem.DomainEventHandler;
import com.mstftrgt.hotelreservationsystem.command.payment.processinhotel.ProcessInHotelPaymentCommand;
import com.mstftrgt.hotelreservationsystem.command.payment.processonline.ProcessOnlinePaymentCommand;
import com.mstftrgt.hotelreservationsystem.cqrs.CommandBus;
import com.mstftrgt.hotelreservationsystem.event.PaymentInitiatedDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentInitiatedDomainEventHandler extends DomainEventHandler<PaymentInitiatedDomainEvent> {

    private final CommandBus commandBus;

    @Override
    public void handleEvent(PaymentInitiatedDomainEvent event) {

        if (event.cardDetails() == null) {
            ProcessInHotelPaymentCommand processInHotelPaymentCommand = ProcessInHotelPaymentCommand.builder()
                    .paymentId(event.paymentId())
                    .build();
            commandBus.dispatch(processInHotelPaymentCommand);
        } else {
            ProcessOnlinePaymentCommand processOnlinePaymentCommand = ProcessOnlinePaymentCommand.builder()
                    .paymentId(event.paymentId())
                    .cardDetails(event.cardDetails())
                    .build();
            commandBus.dispatch(processOnlinePaymentCommand);
        }
    }

}
