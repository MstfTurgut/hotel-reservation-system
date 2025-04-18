package com.mstftrgt.hotelreservationsystem.persistence.entity;

import com.mstftrgt.hotelreservationsystem.model.Room;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room", schema = "roommanagement")
public class RoomEntity {

    @Id
    private UUID id;

    private UUID roomTypeId;

    private String roomNumber;

    public static RoomEntity from(Room room) {
        return RoomEntity.builder()
                .id(room.getId())
                .roomTypeId(room.getRoomTypeId())
                .roomNumber(room.getRoomNumber())
                .build();
    }

    public Room toModel() {
        return Room.builder()
                .id(this.id)
                .roomTypeId(this.roomTypeId)
                .roomNumber(this.roomNumber)
                .build();
    }
}
