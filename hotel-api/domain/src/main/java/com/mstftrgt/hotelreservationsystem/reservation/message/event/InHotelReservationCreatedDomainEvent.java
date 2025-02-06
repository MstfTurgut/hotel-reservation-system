package com.mstftrgt.hotelreservationsystem.reservation.message.event;

import com.mstftrgt.hotelreservationsystem.payment.model.PaymentMethod;
import com.mstftrgt.hotelreservationsystem.reservation.model.CardDetails;
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
@MessageType(name = "IN_HOTEL_RESERVATION_CREATED")
public class InHotelReservationCreatedDomainEvent extends Message {

    private BigDecimal totalPrice;
    private Long reservationId;
    private PaymentMethod paymentMethod;
}
