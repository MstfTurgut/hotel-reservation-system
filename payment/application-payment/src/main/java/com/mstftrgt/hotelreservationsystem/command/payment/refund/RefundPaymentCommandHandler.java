package com.mstftrgt.hotelreservationsystem.command.payment.refund;

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
public class RefundPaymentCommandHandler implements VoidCommandHandler<RefundPaymentCommand> {

    private final PaymentRepository paymentRepository;
    private final PaymentGatewayPort paymentGatewayPort;

    @Override
    public void handle(RefundPaymentCommand command) {
        Payment payment = paymentRepository.findByReservationId(command.reservationId())
                .orElseThrow(PaymentNotFoundException::new);

        try {
            paymentGatewayPort.initiateRefund(payment.getTransactionId());
            payment.markAsRefundInitiated();
        } catch (Exception e) {
            payment.markAsRefundFailed();
        }

        paymentRepository.save(payment);
    }
}
