package com.mstftrgt.hotelreservationsystem.persistence.repository;

import com.mstftrgt.hotelreservationsystem.persistence.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, UUID> {

    List<RoomEntity> findAllByRoomTypeId(UUID roomTypeId);

}
