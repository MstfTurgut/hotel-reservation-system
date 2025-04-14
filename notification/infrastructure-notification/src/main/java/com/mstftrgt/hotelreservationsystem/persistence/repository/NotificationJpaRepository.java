package com.mstftrgt.hotelreservationsystem.persistence.repository;

import com.mstftrgt.hotelreservationsystem.persistence.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, UUID> {
}
