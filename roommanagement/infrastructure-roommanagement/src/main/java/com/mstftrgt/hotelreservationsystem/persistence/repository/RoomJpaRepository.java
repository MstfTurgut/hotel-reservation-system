package com.mstftrgt.hotelreservationsystem.persistence.repository;

import com.mstftrgt.hotelreservationsystem.persistence.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, UUID> {

    List<RoomEntity> findAllByRoomTypeId(UUID roomTypeId);

    @Query(nativeQuery = true, value = "select room_type_id from roommanagement.room where id = ?1")
    UUID findRoomTypeIdByRoomId(UUID roomId);
}
