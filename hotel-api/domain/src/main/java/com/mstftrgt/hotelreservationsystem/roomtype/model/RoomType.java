package com.mstftrgt.hotelreservationsystem.roomtype.model;

import com.mstftrgt.hotelreservationsystem.reservation.model.GuestCount;
import io.craftgate.modulith.messaging.api.model.AggregateRoot;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;


@Slf4j
@Getter
@ToString
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class RoomType extends AggregateRoot {

    private Long id;
    private String title;
    private String description;
    private BigDecimal priceForNight;
    private int numberOfRooms;
    private GuestCount guestCount;
}
