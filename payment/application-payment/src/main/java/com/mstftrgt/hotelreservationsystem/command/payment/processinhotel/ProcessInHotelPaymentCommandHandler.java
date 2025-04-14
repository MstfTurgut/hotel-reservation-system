package com.mstftrgt.hotelreservationsystem.command.payment.processinhotel;

import com.mstftrgt.hotelreservationsystem.cqrs.CommandHandler;
import com.mstftrgt.hotelreservationsystem.exception.PaymentNotFoundException;
import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessInHotelPaymentCommandHandler implements CommandHandler<ProcessInHotelPaymentCommand> {

    private final PaymentRepository paymentRepository;

    @Override
    public void handle(ProcessInHotelPaymentCommand command) {
        Payment payment = paymentRepository.findById(command.paymentId())
                .orElseThrow(() -> new PaymentNotFoundException(command.paymentId()));

        payment.markAsPaid();

        paymentRepository.save(payment);
    }
}
