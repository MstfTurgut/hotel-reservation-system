package com.mstftrgt.hotelreservationsystem.persistence.repository;

import com.mstftrgt.hotelreservationsystem.persistence.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, UUID> {

    List<ReservationEntity> findAllByRoomId(UUID roomId);

    List<ReservationEntity> findAllByFullNameAndPhoneNumber(String fullName, String phoneNumber);

    List<ReservationEntity> findAllByUserId(UUID userId);
}
