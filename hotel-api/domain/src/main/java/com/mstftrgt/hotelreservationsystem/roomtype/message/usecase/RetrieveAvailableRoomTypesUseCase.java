package com.mstftrgt.hotelreservationsystem.roomtype.message.usecase;

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
public class RetrieveAvailableRoomTypesUseCase extends Message {

    private int adultGuestCount;
    private int childGuestCount;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
