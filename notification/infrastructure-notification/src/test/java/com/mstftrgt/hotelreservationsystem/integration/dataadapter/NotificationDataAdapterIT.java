package com.mstftrgt.hotelreservationsystem.integration.dataadapter;


import com.mstftrgt.hotelreservationsystem.notification.model.Notification;
import com.mstftrgt.hotelreservationsystem.notification.model.NotificationType;
import com.mstftrgt.hotelreservationsystem.persistence.NotificationDataAdapter;
import com.mstftrgt.hotelreservationsystem.persistence.entity.NotificationEntity;
import com.mstftrgt.hotelreservationsystem.persistence.repository.NotificationJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Sql(scripts = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dataAdapterIntegrationTest")
public class NotificationDataAdapterIT {

    private NotificationDataAdapter notificationDataAdapter;

    @Autowired
    private NotificationJpaRepository notificationJpaRepository;

    @BeforeEach
    void setUp() {
        this.notificationDataAdapter = new NotificationDataAdapter( notificationJpaRepository);
    }

    @Test
    void save_shouldPersistNotification() {
        Notification notification = Notification.builder()
                .id(UUID.randomUUID())
                .reservationId(UUID.randomUUID())
                .content("testContent")
                .createdAt(LocalDateTime.now())
                .type(NotificationType.PAYMENT_CREATED)
                .build();

        notificationDataAdapter.save(notification);

        Optional<NotificationEntity> result = notificationJpaRepository.findById(notification.getId());

        assertTrue(result.isPresent());
    }

}
