package com.mstftrgt.hotelreservationsystem.payment.model;

import io.craftgate.modulith.messaging.api.model.AggregateRoot;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Slf4j
@Getter
@ToString
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class Payment extends AggregateRoot {

    private Long id;
    private Long reservationId;
    private Long transactionId;
    private BigDecimal price;
    private LocalDateTime date;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;

    public Payment create() {
        return null;
    }
}
