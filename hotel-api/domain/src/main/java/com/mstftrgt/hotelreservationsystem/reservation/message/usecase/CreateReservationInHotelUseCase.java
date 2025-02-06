package com.mstftrgt.hotelreservationsystem.reservation.message.usecase;

import com.mstftrgt.hotelreservationsystem.payment.model.PaymentMethod;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreateReservationInHotelUseCase extends BaseCreateReservationUseCase {

    private PaymentMethod paymentMethod;
}
