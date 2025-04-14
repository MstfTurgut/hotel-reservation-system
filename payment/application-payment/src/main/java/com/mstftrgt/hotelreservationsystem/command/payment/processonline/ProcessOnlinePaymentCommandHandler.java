package com.mstftrgt.hotelreservationsystem.command.payment.processonline;

import java.util.UUID;

import com.mstftrgt.hotelreservationsystem.cqrs.CommandHandler;
import com.mstftrgt.hotelreservationsystem.exception.PaymentNotFoundException;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.port.PaymentGatewayPort;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessOnlinePaymentCommandHandler implements CommandHandler<ProcessOnlinePaymentCommand> {

    private final PaymentRepository paymentRepository;
    private final PaymentGatewayPort paymentGatewayPort;

    @Override
    public void handle(ProcessOnlinePaymentCommand command) {
        Payment payment = paymentRepository.findById(command.paymentId())
                .orElseThrow(() -> new PaymentNotFoundException(command.paymentId()));

        try {
            UUID transactionId = paymentGatewayPort.pay(payment.getAmount(), command.cardDetails());
            payment.setTransactionId(transactionId);
            payment.markAsPaid();
        } catch (Exception e) {
            payment.markAsFailed();
        }

        paymentRepository.save(payment);
    }
}
