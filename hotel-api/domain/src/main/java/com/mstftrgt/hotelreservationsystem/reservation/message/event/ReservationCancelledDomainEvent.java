package com.mstftrgt.hotelreservationsystem.reservation.message.event;

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
@MessageType(name = "RESERVATION_CANCELLED")
public class ReservationCancelledDomainEvent extends Message {

    private Long paymentId;
    private Long reservationId;
}
