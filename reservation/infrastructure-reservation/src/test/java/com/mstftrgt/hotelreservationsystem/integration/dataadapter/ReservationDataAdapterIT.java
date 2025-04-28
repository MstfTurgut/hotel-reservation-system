package com.mstftrgt.hotelreservationsystem.integration.dataadapter;

import com.mstftrgt.hotelreservationsystem.generic.domain.AggregateRoot;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.persistence.ReservationDataAdapter;
import com.mstftrgt.hotelreservationsystem.persistence.entity.ReservationEntity;
import com.mstftrgt.hotelreservationsystem.persistence.repository.ReservationJpaRepository;
import com.mstftrgt.hotelreservationsystem.reservation.dto.ReservationCreate;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCancelledDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCreatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.model.CustomerDetails;
import com.mstftrgt.hotelreservationsystem.reservation.model.GuestSpecification;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.ReservationStatus;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import org.hibernate.annotations.processing.SQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Sql(scripts = "classpath:schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dataAdapterIntegrationTest")
class ReservationDataAdapterIT {

    @MockitoBean
    private ApplicationEventPublisher publisher;

    private ReservationDataAdapter reservationDataAdapter;

    @Autowired
    private ReservationJpaRepository reservationJpaRepository;

    @BeforeEach
    void setUp() {
        this.reservationDataAdapter = new ReservationDataAdapter(publisher, reservationJpaRepository);
    }

    @Test
    void save_shouldPersistReservation() {

        ReservationCreate reservationCreate = ReservationCreate.builder()
                .userId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .roomId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .adultGuestCount(1)
                .childGuestCount(1)
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 3))
                .customerFullName("testFullName")
                .customerEmailAddress("test@example.com")
                .customerPhoneNumber("1234567890")
                .confirmationCode("testConfirmationCode")
                .reservationCode("testReservationCode")
                .totalPrice(BigDecimal.TEN)
                .cardDetails(new CardDetails("testCardNumber", "testHolderName", "testExpiryDate", "testCvv"))
                .build();

        Reservation reservation = Reservation.create(reservationCreate);

        reservationDataAdapter.save(reservation);

        Optional<ReservationEntity> result = reservationJpaRepository.findById(reservation.getId());

        assertTrue(result.isPresent());

        verify(publisher, times(1)).publishEvent(any(ReservationCreatedDomainEvent.class));
    }
}