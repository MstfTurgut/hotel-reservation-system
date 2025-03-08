package com.mstftrgt.hotelreservationsystem.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@Builder
public class RoomType {

    private Long id;
    private String title;
    private String description;
    private BigDecimal priceForNight;
    private int numberOfRooms;
    private int adultCapacity;
    private int childCapacity;

    public void incrementNumberOfRooms() {
        this.numberOfRooms++;
    }

    public void decrementNumberOfRooms() {
        this.numberOfRooms--;
    }

    public void modify(String title, String description, BigDecimal priceForNight, int adultCapacity, int childCapacity) {
        this.title = title;
        this.description = description;
        this.priceForNight = priceForNight;
        this.adultCapacity = adultCapacity;
        this.childCapacity = childCapacity;
    }

    public boolean canFit(int adultGuestCount, int childGuestCount) {
        return (adultCapacity >= adultGuestCount) && (childCapacity >= childGuestCount);
    }
}
