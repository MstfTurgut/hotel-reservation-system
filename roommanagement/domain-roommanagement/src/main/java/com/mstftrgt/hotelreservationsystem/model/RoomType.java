package com.mstftrgt.hotelreservationsystem.model;

import com.mstftrgt.hotelreservationsystem.AggregateRoot;
import com.mstftrgt.hotelreservationsystem.dto.RoomTypeCreate;
import com.mstftrgt.hotelreservationsystem.dto.RoomTypeModify;
import com.mstftrgt.hotelreservationsystem.event.RoomTypeRemovedDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@With
@Builder
@EqualsAndHashCode(callSuper = true)
public class RoomType extends AggregateRoot {

    private UUID id;
    private String title;
    private String description;
    private BigDecimal priceForNight;
    private int numberOfRooms;
    private int adultCapacity;
    private int childCapacity;

    public static RoomType create(RoomTypeCreate roomTypeCreate) {
        return RoomType.builder()
                .id(UUID.randomUUID())
                .title(roomTypeCreate.title())
                .description(roomTypeCreate.description())
                .priceForNight(roomTypeCreate.priceForNight())
                .numberOfRooms(0)
                .adultCapacity(roomTypeCreate.adultCapacity())
                .childCapacity(roomTypeCreate.childCapacity())
                .build();
    }

    public void modify(RoomTypeModify roomTypeModify) {
        this.title = roomTypeModify.title();
        this.description = roomTypeModify.description();
        this.priceForNight = roomTypeModify.priceForNight();
        this.adultCapacity = roomTypeModify.adultCapacity();
        this.childCapacity = roomTypeModify.childCapacity();
    }

    public void remove() {
        this.registerEvent(new RoomTypeRemovedDomainEvent(id));
    }
    public void incrementNumberOfRooms() {
        this.numberOfRooms++;
    }

    public void decrementNumberOfRooms() {
        this.numberOfRooms--;
    }

    public boolean canFit(int adultGuestCount, int childGuestCount) {
        return (adultCapacity >= adultGuestCount) && (childCapacity >= childGuestCount);
    }
}
