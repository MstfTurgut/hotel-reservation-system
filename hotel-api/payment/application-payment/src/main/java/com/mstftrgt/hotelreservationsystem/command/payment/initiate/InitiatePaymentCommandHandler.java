package com.mstftrgt.hotelreservationsystem.command.payment.initiate;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitiatePaymentCommandHandler implements CommandHandler<InitiatePaymentCommand> {

    private final PaymentRepository paymentRepository;

    @Override
    public void handle(InitiatePaymentCommand command) {
        Payment payment = Payment.create(command.toPaymentCreate());

        payment.initiate(command.cardDetails());

        paymentRepository.save(payment);
    }
}
