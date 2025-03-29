package com.mstftrgt.hotelreservationsystem.model;

import com.mstftrgt.hotelreservationsystem.domain.AggregateRoot;
import com.mstftrgt.hotelreservationsystem.event.PaymentCompletedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentFailedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.PaymentInitiatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundFailedDomainEvent;
import com.mstftrgt.hotelreservationsystem.event.RefundInitiatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.exception.PaymentAlreadyRefundedException;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.dto.PaymentCreate;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@With
@Builder
@EqualsAndHashCode(callSuper = true)
public class Payment extends AggregateRoot {

    private UUID id;
    private UUID transactionId;
    private UUID reservationId;
    private BigDecimal amount;
    private LocalDateTime createDate;
    private PaymentStatus status;


    public static Payment create(PaymentCreate paymentCreate) {
        return Payment.builder()
                .id(UUID.randomUUID())
                .reservationId(paymentCreate.reservationId())
                .amount(paymentCreate.amount())
                .createDate(LocalDateTime.now())
                .build();
    }

    public void initiate(CardDetails cardDetails) {
        status = PaymentStatus.PENDING;
        this.registerEvent(new PaymentInitiatedDomainEvent(id, cardDetails));
    }

    public void markAsRefundInitiated() {
        if(status == PaymentStatus.REFUND_INITIATED) {
            throw new PaymentAlreadyRefundedException();
        }
        status = PaymentStatus.REFUND_INITIATED;
        this.registerEvent(new RefundInitiatedDomainEvent(reservationId, amount));
    }

    public void markAsRefundFailed() {
        status = PaymentStatus.REFUND_FAILED;
        this.registerEvent(new RefundFailedDomainEvent(reservationId, amount));
    }

    public void markAsPaid() {
        status = PaymentStatus.PAID;
        this.registerEvent(new PaymentCompletedDomainEvent(reservationId, amount));
    }

    public void markAsFailed() {
        status = PaymentStatus.FAILED;
        this.registerEvent(new PaymentFailedDomainEvent(reservationId, amount));
    }

}
