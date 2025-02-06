package com.mstftrgt.hotelreservationsystem.payment.message.event;

import io.craftgate.modulith.messaging.api.annotation.MessageType;
import io.craftgate.modulith.messaging.api.model.Message;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@MessageType(name = "REFUND_CANNOT_BE_INITIALIZED")
public class RefundCannotBeInitializedDomainEvent extends Message {

    private Long reservationId;
}
