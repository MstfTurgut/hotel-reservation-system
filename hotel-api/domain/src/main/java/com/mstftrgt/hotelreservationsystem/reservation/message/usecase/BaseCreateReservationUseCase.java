package com.mstftrgt.hotelreservationsystem.reservation.message.usecase;

import io.craftgate.modulith.messaging.api.model.Message;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class BaseCreateReservationUseCase extends Message {

    private Long userId;
    private Long RoomTypeId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private int adultCount;
    private int childCount;
    private String name;
    private String surname;
    private String phoneNumber;
    private String emailAddress;
}
