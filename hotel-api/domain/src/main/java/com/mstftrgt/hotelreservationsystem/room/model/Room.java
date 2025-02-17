package com.mstftrgt.hotelreservationsystem.room.model;

import com.mstftrgt.hotelreservationsystem.room.message.usecase.AddRoomUseCase;
import io.craftgate.modulith.messaging.api.model.AggregateRoot;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class Room extends AggregateRoot {

    private Long id;
    private Long roomTypeId;
    private String roomNumber;

    public static Room create(AddRoomUseCase message) {
        return Room.builder()
                .roomTypeId(message.getRoomTypeId())
                .roomNumber(message.getRoomNumber())
                .build();
    }
}
