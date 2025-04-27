package com.mstftrgt.hotelreservationsystem.persistence.repository;

import com.mstftrgt.hotelreservationsystem.persistence.entity.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomTypeJpaRepository extends JpaRepository<RoomTypeEntity, UUID> {

}
