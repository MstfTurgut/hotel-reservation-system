package com.mstftrgt.hotelreservationsystem.roomtype.model;

import com.mstftrgt.hotelreservationsystem.reservation.model.GuestCount;
import com.mstftrgt.hotelreservationsystem.roomtype.message.usecase.AddRoomTypeUseCase;
import com.mstftrgt.hotelreservationsystem.roomtype.message.usecase.ModifyRoomTypeUseCase;
import io.craftgate.modulith.messaging.api.model.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;


@Getter
@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class RoomType extends AggregateRoot {

    private Long id;
    private String title;
    private String description;
    private BigDecimal priceForNight;
    private int numberOfRooms;
    private int adultCapacity;
    private int childCapacity;

    public static RoomType create(AddRoomTypeUseCase message) {
        return RoomType.builder()
                .title(message.getTitle())
                .description(message.getDescription())
                .priceForNight(message.getPriceForNight())
                .numberOfRooms(0)
                .adultCapacity(message.getAdultCapacity())
                .childCapacity(message.getChildCapacity())
                .build();
    }

    public void modify(ModifyRoomTypeUseCase message) {
        this.title = message.getTitle();
        this.description = message.getDescription();
        this.priceForNight = message.getPriceForNight();
        this.adultCapacity = message.getAdultCapacity();
        this.childCapacity = message.getChildCapacity();
    }
    public void increaseNumberOfRooms() {
        numberOfRooms++;
    }

}
