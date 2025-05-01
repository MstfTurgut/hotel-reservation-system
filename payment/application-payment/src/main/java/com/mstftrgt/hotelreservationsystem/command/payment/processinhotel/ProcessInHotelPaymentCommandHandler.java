package com.mstftrgt.hotelreservationsystem.command.payment.processinhotel;

import com.mstftrgt.hotelreservationsystem.dto.PaymentCreate;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessInHotelPaymentCommandHandler implements VoidCommandHandler<ProcessInHotelPaymentCommand> {

    private final PaymentRepository paymentRepository;

    @Override
    public void handle(ProcessInHotelPaymentCommand command) {
        Payment payment = Payment.create(buildPaymentCreate(command));

        payment.markAsCompleted();

        paymentRepository.save(payment);
    }

    private static PaymentCreate buildPaymentCreate(ProcessInHotelPaymentCommand command) {
        return PaymentCreate.builder()
                .reservationId(command.reservationId())
                .amount(command.paymentAmount())
                .build();
    }
}
