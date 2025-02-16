package com.mstftrgt.hotelreservationsystem.roomtype.message.usecase;

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
public class ModifyRoomTypeUseCase extends Message {

    private Long id;
    private String title;
    private String description;
    private BigDecimal priceForNight;
    private int adultCapacity;
    private int childCapacity;
}
