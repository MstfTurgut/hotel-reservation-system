package com.mstftrgt.hotelreservationsystem.command.payment.create;


import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.DomainEventPublisher;
import com.mstftrgt.hotelreservationsystem.repository.dto.PaymentCreate;
import com.mstftrgt.hotelreservationsystem.event.PaymentCreated;
import com.mstftrgt.hotelreservationsystem.repository.PaymentRepository;
import com.mstftrgt.hotelreservationsystem.model.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePaymentCommandHandler implements CommandHandler<CreatePaymentCommand> {

    private final PaymentRepository paymentRepository;
    private final DomainEventPublisher eventPublisher;

    @Override
    public void handle(CreatePaymentCommand command) {
        paymentRepository.save(buildPaymentCreate(command));

        eventPublisher.publish(new PaymentCreated(command.getReservationId(), command.getTotalPrice()));
    }

    private static PaymentCreate buildPaymentCreate(CreatePaymentCommand command) {
        return PaymentCreate.builder()
                .amount(command.getTotalPrice())
                .reservationId(command.getReservationId())
                .transactionId(command.getTransactionId())
                .status(PaymentStatus.PAID)
                .build();
    }
}
