package com.mstftrgt.hotelreservationsystem.persistence.entity;

import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.model.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class PaymentEntity {

    @Id
    private UUID id;
    private UUID transactionId;
    private UUID reservationId;
    private BigDecimal amount;
    private LocalDateTime createDate;
    private String status;

    public static PaymentEntity from(Payment payment) {
        return PaymentEntity.builder()
                .id(payment.getId())
                .transactionId(payment.getTransactionId())
                .reservationId(payment.getReservationId())
                .amount(payment.getAmount())
                .createDate(payment.getCreateDate())
                .status(payment.getStatus().name())
                .build();
    }

    public Payment toModel() {
        return Payment.builder()
                .id(id)
                .transactionId(transactionId)
                .reservationId(reservationId)
                .amount(amount)
                .createDate(createDate)
                .status(PaymentStatus.valueOf(status))
                .build();
    }
}
