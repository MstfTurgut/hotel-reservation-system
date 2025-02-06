package com.mstftrgt.hotelreservationsystem.reservation.message.usecase;

import io.craftgate.modulith.messaging.api.model.Message;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class CancelReservationUseCase extends Message {

    private Long reservationId;
    private String confirmationCode;
}
