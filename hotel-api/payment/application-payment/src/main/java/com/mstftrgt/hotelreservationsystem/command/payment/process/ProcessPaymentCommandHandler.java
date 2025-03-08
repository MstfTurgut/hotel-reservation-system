package com.mstftrgt.hotelreservationsystem.command.payment.process;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.DomainEventPublisher;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailed;
import com.mstftrgt.hotelreservationsystem.event.PaymentProcessed;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessPaymentCommandHandler implements CommandHandler<ProcessPaymentCommand> {

    private final PaymentRepository paymentRepository;
    private final DomainEventPublisher eventPublisher;

    @Override
    public void handle(ProcessPaymentCommand command) {
        try {
            long transactionId = paymentRepository.pay(command.getAmount(), command.getCardDetails());
            eventPublisher.publish(new PaymentProcessed(command.getAmount(), command.getReservationId(), transactionId));
        } catch (Exception e) {
            eventPublisher.publish(new PaymentFailed(command.getReservationId()));
        }
    }
}
