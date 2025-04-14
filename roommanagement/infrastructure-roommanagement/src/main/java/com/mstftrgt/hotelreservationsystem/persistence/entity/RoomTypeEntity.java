package com.mstftrgt.hotelreservationsystem.persistence.entity;

import com.mstftrgt.hotelreservationsystem.model.RoomType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room_type")
public class RoomTypeEntity {

    @Id
    private UUID id;

    private String title;

    private String description;

    private BigDecimal priceForNight;

    private int numberOfRooms;

    private int adultCapacity;

    private int childCapacity;

    public static RoomTypeEntity from(RoomType roomType) {
        return RoomTypeEntity.builder()
                .id(roomType.getId())
                .title(roomType.getTitle())
                .description(roomType.getDescription())
                .priceForNight(roomType.getPriceForNight())
                .numberOfRooms(roomType.getNumberOfRooms())
                .adultCapacity(roomType.getAdultCapacity())
                .childCapacity(roomType.getChildCapacity())
                .build();
    }

    public RoomType toModel() {
        return RoomType.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .priceForNight(this.priceForNight)
                .numberOfRooms(this.numberOfRooms)
                .adultCapacity(this.adultCapacity)
                .childCapacity(this.childCapacity)
                .build();
    }
}
