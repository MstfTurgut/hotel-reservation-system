package com.mstftrgt.hotelreservationsystem.command.payment.processonline;

import java.util.UUID;

import com.mstftrgt.hotelreservationsystem.command.payment.processinhotel.ProcessInHotelPaymentCommand;
import com.mstftrgt.hotelreservationsystem.dto.PaymentCreate;
import com.mstftrgt.hotelreservationsystem.exception.PaymentNotFoundException;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandHandler;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.port.PaymentGatewayPort;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessOnlinePaymentCommandHandler implements VoidCommandHandler<ProcessOnlinePaymentCommand> {

    private final PaymentRepository paymentRepository;
    private final PaymentGatewayPort paymentGatewayPort;

    @Override
    public void handle(ProcessOnlinePaymentCommand command) {
        Payment payment = Payment.create(buildPaymentCreate(command));

        try {
            UUID transactionId = paymentGatewayPort.pay(payment.getAmount(), command.cardDetails());
            payment.setTransactionId(transactionId);
            payment.markAsPaid();
        } catch (Exception e) {
            payment.markAsFailed();
        }

        paymentRepository.save(payment);
    }

    private static PaymentCreate buildPaymentCreate(ProcessOnlinePaymentCommand command) {
        return PaymentCreate.builder()
                .reservationId(command.reservationId())
                .amount(command.paymentAmount())
                .build();
    }
}
