package com.mstftrgt.hotelreservationsystem.payment.message.event;

import com.mstftrgt.hotelreservationsystem.payment.model.PaymentMethod;
import io.craftgate.modulith.messaging.api.annotation.MessageType;
import io.craftgate.modulith.messaging.api.model.Message;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@MessageType(name = "PAYMENT_PROCESSED")
public class PaymentProcessedDomainEvent extends Message {

    private BigDecimal totalPrice;
    private Long reservationId;
    private PaymentMethod paymentMethod;
    private Long transactionId;
}
