package com.mstftrgt.hotelreservationsystem.command.payment.initiate;

import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.dto.PaymentCreate;
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
        Payment payment = Payment.create(buildPaymentCreate(command));

        payment.initiate(command.cardDetails());

        paymentRepository.save(payment);
    }

    private static PaymentCreate buildPaymentCreate(InitiatePaymentCommand command) {
        return PaymentCreate.builder()
                .reservationId(command.reservationId())
                .amount(command.paymentAmount())
                .build();
    }
}
