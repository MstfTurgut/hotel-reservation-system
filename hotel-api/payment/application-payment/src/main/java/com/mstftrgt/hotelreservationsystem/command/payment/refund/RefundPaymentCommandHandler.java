package com.mstftrgt.hotelreservationsystem.command.payment.refund;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.DomainEventPublisher;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import com.mstftrgt.hotelreservationsystem.event.RefundFailed;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundPaymentCommandHandler implements CommandHandler<RefundPaymentCommand> {

    private final PaymentRepository paymentRepository;
    private final DomainEventPublisher eventPublisher;

    @Override
    public void handle(RefundPaymentCommand command) {
        Payment payment = paymentRepository.findByReservationId(command.getReservationId()).orElseThrow(
                () -> new IllegalArgumentException("Payment not found"));

        try {
            payment.refund();
            paymentRepository.initializeRefund(payment.getTransactionId());
            eventPublisher.publish(new RefundInitiated(command.getReservationId()));
        } catch (Exception e) {
            payment.setPaid();
            eventPublisher.publish(new RefundFailed(command.getReservationId()));
        }
    }
}
