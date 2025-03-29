package com.mstftrgt.hotelreservationsystem.model;

import com.mstftrgt.hotelreservationsystem.dto.RoomTypeCreate;
import com.mstftrgt.hotelreservationsystem.dto.RoomTypeModify;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.UUID;

@Data
@With
@Builder
public class RoomType {

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
