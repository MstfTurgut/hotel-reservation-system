package com.mstftrgt.hotelreservationsystem.reservation.message.usecase;

import com.mstftrgt.hotelreservationsystem.reservation.model.CardDetails;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreateReservationByCallUseCase extends BaseCreateReservationUseCase {

    private CardDetails cardDetails;
}
